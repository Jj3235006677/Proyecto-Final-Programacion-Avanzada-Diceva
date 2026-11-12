package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.AgregarJuegoUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.AplicarCuponUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.ConfirmarCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.ConsultarCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.CrearCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.RealizarRembolsoUseCase;

import com.uniquindio.ecommerce.application.dto.response.CompraResponse;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    private final AplicarCuponUseCase aplicarCuponUseCase;
    private final CompraMapper mapper;
    private final UsuarioRepository usuarioRepository;

    public CompraController(
            CrearCompraUseCase crearCompraUseCase,
            ConfirmarCompraUseCase confirmarCompraUseCase,
            RealizarRembolsoUseCase realizarRembolsoUseCase,
            AgregarJuegoUseCase agregarJuegoUseCase,
            ConsultarCompraUseCase consultarCompraUseCase,
            AplicarCuponUseCase aplicarCuponUseCase,
            CompraMapper mapper,
            UsuarioRepository usuarioRepository) {

        this.crearCompraUseCase = crearCompraUseCase;
        this.confirmarCompraUseCase = confirmarCompraUseCase;
        this.realizarRembolsoUseCase = realizarRembolsoUseCase;
        this.agregarJuegoUseCase = agregarJuegoUseCase;
        this.consultarCompraUseCase = consultarCompraUseCase;
        this.aplicarCuponUseCase = aplicarCuponUseCase;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Crear una compra con la cédula del usuario autenticado.
     */
    @PostMapping
    public ResponseEntity<CompraResponse> crear(
            Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "El usuario debe iniciar sesión"
            );
        }

        String email = authentication.getName();

        var usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "El usuario autenticado no existe"
                ));

        String cedula = usuario.getCedula();

        UUID id = UUID.randomUUID();

        Compra compra = crearCompraUseCase.ejecutar(
                id,
                cedula,
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

        return ResponseEntity.ok(mapper.toResponse(compra));
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
     * Aplicar un cupón de descuento a una compra pendiente del usuario autenticado.
     */
    @PatchMapping("/{id}/cupon")
    public ResponseEntity<CompraResponse> aplicarCupon(
            @PathVariable UUID id,
            @RequestParam
            @NotBlank(message = "El código del cupón es obligatorio")
            String codigo,
            Authentication authentication) {

        String cedula = usuarioRepository.buscarPorEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "El usuario autenticado no existe"
                ))
                .getCedula();

        Compra compra = aplicarCuponUseCase.ejecutar(id, codigo, cedula);

        return ResponseEntity.ok(mapper.toResponse(compra));
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