package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.ListarComprasPendientesUseCase;
import com.uniquindio.ecommerce.application.dto.response.CompraResponse;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/compras")
public class AdminCompraController {

    private final ListarComprasPendientesUseCase listarComprasPendientesUseCase;
    private final CompraMapper mapper;

    public AdminCompraController(
            ListarComprasPendientesUseCase listarComprasPendientesUseCase,
            CompraMapper mapper) {

        this.listarComprasPendientesUseCase = listarComprasPendientesUseCase;
        this.mapper = mapper;
    }

    // Compras pendientes de todos los usuarios, para que el ADMIN las confirme
    @GetMapping("/pendientes")
    public ResponseEntity<List<CompraResponse>> pendientes() {

        List<CompraResponse> respuesta = listarComprasPendientesUseCase.ejecutar()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(respuesta);
    }
}