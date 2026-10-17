package com.uniquindio.ecommerce.infrastructure.rest.controller;
//Vamos bien

import com.uniquindio.ecommerce.application.UseCase.*;
import com.uniquindio.ecommerce.application.dto.request.RealizarCompraRequest;
import com.uniquindio.ecommerce.application.dto.response.CompraResponse;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final RealizarCompraUseCase realizarCompraUseCase;
    private final ConfirmarCompraUseCase confirmarCompraUseCase;
    private final RealizarRembolsoUseCase realizarRembolsoUseCase;
    private final AgregarJuegoUseCase agregarJuegoUseCase;
    private final ConsultarCompraUseCase consultarCompraUseCase;
    private final CompraMapper mapper;

    public CompraController(RealizarCompraUseCase realizarCompraUseCase, ConfirmarCompraUseCase confirmarCompraUseCase, RealizarRembolsoUseCase realizarRembolsoUseCase, AgregarJuegoUseCase agregarJuegoUseCase,ConsultarCompraUseCase consultarCompraUseCase, CompraMapper mapper) {
        this.realizarCompraUseCase = realizarCompraUseCase;
        this.confirmarCompraUseCase = confirmarCompraUseCase;
        this.realizarRembolsoUseCase = realizarRembolsoUseCase;
        this.agregarJuegoUseCase = agregarJuegoUseCase;
        this.consultarCompraUseCase = consultarCompraUseCase;
        this.mapper = mapper;
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmarCompra(@PathVariable UUID id) {

        confirmarCompraUseCase.ejecutar(id);

        return ResponseEntity.noContent().build();
    }




    @PatchMapping("/{id}/reembolsar")
    public ResponseEntity<Void> reembolsarCompra(@PathVariable UUID id,String motivo) {

        realizarRembolsoUseCase.ejecutar(motivo,id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> obtenerCompra(@PathVariable UUID id) {

        Compra compra = consultarCompraUseCase.ejecutar(id);

        return ResponseEntity.ok(mapper.toResponse(compra));
    }




    @PatchMapping("/agregarJuego")
    public ResponseEntity<Void> agregarJuego(@RequestParam UUID idJuego, @RequestParam int cantidad, @RequestParam UUID idCompra) {

        agregarJuegoUseCase.ejecutar(idJuego, cantidad, idCompra);

        return ResponseEntity.noContent().build();
    }





    @PostMapping
    public ResponseEntity<CompraResponse> crear(@Valid @RequestBody RealizarCompraRequest request) {

        UUID id = UUID.randomUUID();

        Compra compra = realizarCompraUseCase.ejecutar(
                id,
                request.idListaJuegosMesa(),
                request.cedulaUsuario(),
                LocalDateTime.now()
        );

        CompraResponse response = mapper.toResponse(compra);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(compra.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }

}