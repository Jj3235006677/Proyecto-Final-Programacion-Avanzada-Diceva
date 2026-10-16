package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.AgregarJuegoUseCase;
import com.uniquindio.ecommerce.application.UseCase.ConsultarJuegoMesaUseCase;
import com.uniquindio.ecommerce.application.UseCase.CrearJuegoMesaUseCase;
import com.uniquindio.ecommerce.application.dto.request.JuegoMesaRequest;
import com.uniquindio.ecommerce.application.dto.request.RealizarCompraRequest;
import com.uniquindio.ecommerce.application.dto.response.CompraResponse;
import com.uniquindio.ecommerce.application.dto.response.JuegoMesaResponse;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.JuegoMesaMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/juegoMesa")
public class JuegoMesaController {
    private final JuegoMesaMapper juegoMesaMapper;
    private final CrearJuegoMesaUseCase crearJuegoMesaUseCase;
    private final ConsultarJuegoMesaUseCase consultarJuegoMesaUseCase;


    public JuegoMesaController(JuegoMesaMapper juegoMesaMapper, CrearJuegoMesaUseCase crearJuegoMesaUseCase,ConsultarJuegoMesaUseCase consultarJuegoMesaUseCase) {
        this.juegoMesaMapper = juegoMesaMapper;
        this.crearJuegoMesaUseCase = crearJuegoMesaUseCase;
        this.consultarJuegoMesaUseCase = consultarJuegoMesaUseCase;

    }
    @GetMapping("/{id}")
    public ResponseEntity<JuegoMesaResponse> obtenerPorId(
            @PathVariable UUID id) {

        JuegoMesa juegoMesa = consultarJuegoMesaUseCase.ejecutar(id);

        return ResponseEntity.ok(juegoMesaMapper.toResponse(juegoMesa));
    }


    @PostMapping
    public ResponseEntity<JuegoMesaResponse> crear(@Valid @RequestBody JuegoMesaRequest juegoMesaRequest) {
        UUID id=UUID.randomUUID();
        JuegoMesa juegoMesa=crearJuegoMesaUseCase.ejecutar(juegoMesaRequest.cedulaProveedor(),id, juegoMesaRequest.nombre(), juegoMesaRequest.descripcion(), juegoMesaRequest.precio(), juegoMesaRequest.stock(),juegoMesaRequest.rangoJugadores(),juegoMesaRequest.complejidad(), juegoMesaRequest.mecanicaDeJuego(), juegoMesaRequest.tematicaDeJuego(), juegoMesaRequest.edadRecomendada(),juegoMesaRequest.imagenUrl(),juegoMesaRequest.estado());
        JuegoMesaResponse response=juegoMesaMapper.toResponse(juegoMesa);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(juegoMesa.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);

    }


}
