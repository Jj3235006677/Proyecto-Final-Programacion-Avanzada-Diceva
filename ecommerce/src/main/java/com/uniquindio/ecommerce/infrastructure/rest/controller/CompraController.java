package com.uniquindio.ecommerce.infrastructure.rest.controller;
//Vamos bien

import com.uniquindio.ecommerce.application.UseCase.RealizarCompraUseCase;
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
-
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final RealizarCompraUseCase realizarCompraUseCase;
    private final CompraMapper mapper;

    public CompraController(
            RealizarCompraUseCase realizarCompraUseCase,
            CompraMapper mapper) {

        this.realizarCompraUseCase = realizarCompraUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CompraResponse> crear(@Valid @RequestBody RealizarCompraRequest request) {

        UUID id = UUID.randomUUID();

        Compra compra = realizarCompraUseCase.ejecutar(
                id,
                request.idListaJuegosMesa(),
                request.cedulaUsuario(),
                request.precioTotal(),
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