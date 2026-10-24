package com.uniquindio.ecommerce.infrastructure.rest.controller;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.AgregarJuegoUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.ConfirmarCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.ConsultarCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.CrearCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.RealizarRembolsoUseCase;

import com.uniquindio.ecommerce.application.dto.request.RealizarCompraRequest;
import com.uniquindio.ecommerce.application.dto.response.CompraResponse;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/compras")
@Validated
public class CompraController {

    private final CrearCompraUseCase crearCompraUseCase;
    private final ConfirmarCompraUseCase confirmarCompraUseCase;
    private final RealizarRembolsoUseCase realizarRembolsoUseCase;
    private final AgregarJuegoUseCase agregarJuegoUseCase;
    private final ConsultarCompraUseCase consultarCompraUseCase;
    private final CompraMapper mapper;

    public CompraController(
            CrearCompraUseCase crearCompraUseCase,
            ConfirmarCompraUseCase confirmarCompraUseCase,
            RealizarRembolsoUseCase realizarRembolsoUseCase,
            AgregarJuegoUseCase agregarJuegoUseCase,
            ConsultarCompraUseCase consultarCompraUseCase,
            CompraMapper mapper) {

        this.crearCompraUseCase = crearCompraUseCase;
        this.confirmarCompraUseCase = confirmarCompraUseCase;
        this.realizarRembolsoUseCase = realizarRembolsoUseCase;
        this.agregarJuegoUseCase = agregarJuegoUseCase;
        this.consultarCompraUseCase = consultarCompraUseCase;
        this.mapper = mapper;
    }

    /**
     * Crear una compra.
     */
    @PostMapping
    public ResponseEntity<CompraResponse> crear(
            @Valid @RequestBody RealizarCompraRequest request) {

        UUID id = UUID.randomUUID();

        Compra compra = crearCompraUseCase.ejecutar(
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

    /**
     * Consultar una compra por su identificador.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> obtenerCompra(
            @PathVariable UUID id) {

        Compra compra = consultarCompraUseCase.ejecutar(id);

        return ResponseEntity.ok(
                mapper.toResponse(compra)
        );
    }

    /**
     * Confirmar una compra.
     */
    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmarCompra(
            @PathVariable UUID id) {

        confirmarCompraUseCase.ejecutar(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Solicitar el reembolso de una compra.
     */
    @PatchMapping("/{id}/reembolsar")
    public ResponseEntity<Void> reembolsarCompra(
            @PathVariable UUID id,
            @RequestParam
            @NotBlank(message = "El motivo del reembolso es obligatorio")
            String motivo) {

        realizarRembolsoUseCase.ejecutar(motivo, id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Agregar un juego de mesa a una compra.
     */
    @PatchMapping("/agregarJuego")
    public ResponseEntity<Void> agregarJuego(
            @RequestParam UUID idJuego,
            @RequestParam
            @Min(value = 1, message = "La cantidad debe ser mayor que cero")
            int cantidad,
            @RequestParam UUID idCompra) {

        agregarJuegoUseCase.ejecutar(
                idJuego,
                cantidad,
                idCompra
        );

        return ResponseEntity.noContent().build();
    }
}