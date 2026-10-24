package com.uniquindio.ecommerce.infrastructure.rest.controller;



import com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.BuscarJuegosPaginadosUseCase;
import com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.ConsultarJuegoMesaUseCase;
import com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.CrearJuegoMesaUseCase;
import com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.ObtenerRankingJuegosUseCase;
import com.uniquindio.ecommerce.application.dto.request.JuegoMesaRequest;
import com.uniquindio.ecommerce.application.dto.response.JuegoMesaResponse;
import com.uniquindio.ecommerce.application.dto.response.RankingJuegoResponse;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.valueobject.ResultadoPaginado;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.JuegoMesaMapper;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/juegoMesa")
public class JuegoMesaController {

    private final JuegoMesaMapper juegoMesaMapper;
    private final CrearJuegoMesaUseCase crearJuegoMesaUseCase;
    private final ConsultarJuegoMesaUseCase consultarJuegoMesaUseCase;
    private final BuscarJuegosPaginadosUseCase buscarJuegosPaginadosUseCase;
    private final ObtenerRankingJuegosUseCase obtenerRankingJuegosUseCase;

    public JuegoMesaController(
            JuegoMesaMapper juegoMesaMapper,
            CrearJuegoMesaUseCase crearJuegoMesaUseCase,
            ConsultarJuegoMesaUseCase consultarJuegoMesaUseCase,
            BuscarJuegosPaginadosUseCase buscarJuegosPaginadosUseCase,ObtenerRankingJuegosUseCase obtenerRankingJuegosUseCase) {

        this.juegoMesaMapper = juegoMesaMapper;
        this.crearJuegoMesaUseCase = crearJuegoMesaUseCase;
        this.consultarJuegoMesaUseCase = consultarJuegoMesaUseCase;
        this.buscarJuegosPaginadosUseCase = buscarJuegosPaginadosUseCase;
        this.obtenerRankingJuegosUseCase=obtenerRankingJuegosUseCase;
    }

    // Consultar juegos de mesa con búsqueda, filtro y paginación
    @GetMapping
    public ResponseEntity<ResultadoPaginado<JuegoMesaResponse>> buscarPaginado(
            @RequestParam(required = false) String texto,
            @RequestParam(required = false) Integer precioMaximo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {

        ResultadoPaginado<JuegoMesa> resultado =
                buscarJuegosPaginadosUseCase.ejecutar(
                        texto,
                        precioMaximo,
                        pagina,
                        tamanio
                );

        ResultadoPaginado<JuegoMesaResponse> respuesta =
                new ResultadoPaginado<>(
                        resultado.contenido().stream()
                                .map(juegoMesaMapper::toResponse)
                                .toList(),
                        resultado.totalElementos(),
                        resultado.totalPaginas(),
                        resultado.paginaActual(),
                        resultado.tamanioPagina()
                );

        return ResponseEntity.ok(respuesta);
    }

    // Consultar un juego de mesa por su identificador
    @GetMapping("/{id}")
    public ResponseEntity<JuegoMesaResponse> obtenerPorId(
            @PathVariable UUID id) {

        JuegoMesa juegoMesa = consultarJuegoMesaUseCase.ejecutar(id);

        return ResponseEntity.ok(
                juegoMesaMapper.toResponse(juegoMesa)
        );
    }

    // Crear un juego de mesa
    @PostMapping
    public ResponseEntity<JuegoMesaResponse> crear(
            @Valid @RequestBody JuegoMesaRequest juegoMesaRequest) {

        UUID id = UUID.randomUUID();

        JuegoMesa juegoMesa = crearJuegoMesaUseCase.ejecutar(
                juegoMesaRequest.cedulaProveedor(),
                id,
                juegoMesaRequest.nombre(),
                juegoMesaRequest.descripcion(),
                juegoMesaRequest.precio(),
                juegoMesaRequest.stock(),
                juegoMesaRequest.rangoJugadores(),
                juegoMesaRequest.complejidad(),
                juegoMesaRequest.mecanicaDeJuego(),
                juegoMesaRequest.tematicaDeJuego(),
                juegoMesaRequest.edadRecomendada(),
                juegoMesaRequest.imagenUrl(),
                juegoMesaRequest.estado()
        );

        JuegoMesaResponse response = juegoMesaMapper.toResponse(juegoMesa);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(juegoMesa.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }

    // Consultar el ranking de juegos más incluidos en compras
    @GetMapping("/ranking")
    public ResponseEntity<List<RankingJuegoResponse>> obtenerRanking() {

        List<Object[]> resultados = obtenerRankingJuegosUseCase.ejecutar();

        List<RankingJuegoResponse> respuesta = resultados.stream()
                .map(fila -> new RankingJuegoResponse(
                        (String) fila[0],
                        ((Number) fila[1]).longValue()
                ))
                .toList();

        return ResponseEntity.ok(respuesta);
    }


}
