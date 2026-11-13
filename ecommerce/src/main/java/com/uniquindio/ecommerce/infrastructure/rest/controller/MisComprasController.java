package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.ListarMisComprasUseCase;
import com.uniquindio.ecommerce.application.dto.response.CompraResponse;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class MisComprasController {

    private final ListarMisComprasUseCase listarMisComprasUseCase;
    private final UsuarioRepository usuarioRepository;
    private final CompraMapper mapper;

    public MisComprasController(
            ListarMisComprasUseCase listarMisComprasUseCase,
            UsuarioRepository usuarioRepository,
            CompraMapper mapper) {

        this.listarMisComprasUseCase = listarMisComprasUseCase;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    // Historial de compras del usuario autenticado, de la más reciente a la más antigua
    @GetMapping("/mias")
    public ResponseEntity<List<CompraResponse>> misCompras(Authentication authentication) {

        String cedula = usuarioRepository.buscarPorEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "El usuario autenticado no existe"
                ))
                .getCedula();

        List<CompraResponse> respuesta = listarMisComprasUseCase.ejecutar(cedula)
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(respuesta);
    }
}