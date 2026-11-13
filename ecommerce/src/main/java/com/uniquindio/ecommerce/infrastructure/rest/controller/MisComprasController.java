package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.CancelarCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.ListarMisComprasUseCase;
import com.uniquindio.ecommerce.application.dto.response.CompraResponse;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.UsuarioRepository;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/compras")
public class MisComprasController {

    private final ListarMisComprasUseCase listarMisComprasUseCase;
    private final CancelarCompraUseCase cancelarCompraUseCase;
    private final UsuarioRepository usuarioRepository;
    private final CompraMapper mapper;

    public MisComprasController(
            ListarMisComprasUseCase listarMisComprasUseCase,
            CancelarCompraUseCase cancelarCompraUseCase,
            UsuarioRepository usuarioRepository,
            CompraMapper mapper) {

        this.listarMisComprasUseCase = listarMisComprasUseCase;
        this.cancelarCompraUseCase = cancelarCompraUseCase;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    // Historial de compras del usuario autenticado, de la más reciente a la más antigua
    @GetMapping("/mias")
    public ResponseEntity<List<CompraResponse>> misCompras(Authentication authentication) {

        List<CompraResponse> respuesta = listarMisComprasUseCase.ejecutar(cedulaDe(authentication))
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    // Cancelar una compra pendiente: el dueño cancela la suya, el ADMIN cualquiera
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<CompraResponse> cancelar(
            @PathVariable UUID id,
            Authentication authentication) {

        boolean esAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(autoridad -> autoridad.getAuthority().equals("ADMIN"));

        Compra compra = cancelarCompraUseCase.ejecutar(id, cedulaDe(authentication), esAdmin);

        return ResponseEntity.ok(mapper.toResponse(compra));
    }

    private String cedulaDe(Authentication authentication) {
        return usuarioRepository.buscarPorEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "El usuario autenticado no existe"
                ))
                .getCedula();
    }
}