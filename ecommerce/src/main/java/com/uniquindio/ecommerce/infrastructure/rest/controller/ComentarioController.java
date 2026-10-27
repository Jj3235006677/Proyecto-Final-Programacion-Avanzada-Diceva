
package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.ComentarioUseCase.RealizarComentarioUseCase;
import com.uniquindio.ecommerce.application.dto.request.RealizarComentarioRequest;
import com.uniquindio.ecommerce.application.dto.response.RealizarComentarioResponse;
import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.ComentarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/Comentario")
@Validated
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioMapper comentarioMapper;
    private final RealizarComentarioUseCase realizarComentarioUseCase;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<RealizarComentarioResponse> realizarComentario(
            @Valid @RequestBody RealizarComentarioRequest request,
            @AuthenticationPrincipal Jwt usuarioAutenticado) {

        UUID idComentario = UUID.randomUUID();

        String usuarioId = usuarioAutenticado.getClaimAsString("uid");

        Comentario comentario = realizarComentarioUseCase.ejecutar(
                idComentario,
                request.codigoJuegoMesa(),
                usuarioId,
                request.titulo(),
                request.calificacion(),
                request.comentario()
        );

        RealizarComentarioResponse response =
                comentarioMapper.toResponse(comentario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(comentario.getIdComentario())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }
}
