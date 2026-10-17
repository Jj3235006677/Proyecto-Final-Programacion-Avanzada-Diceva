package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase.ConsultarUsuarioUseCase;
import com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase.CrearUsuarioUseCase;
import com.uniquindio.ecommerce.application.dto.request.UsuarioRequest;
import com.uniquindio.ecommerce.application.dto.response.JuegoMesaResponse;
import com.uniquindio.ecommerce.application.dto.response.UsuarioResponse;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.entity.Usuario;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.UsuarioMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final CrearUsuarioUseCase crearUsuarioUseCase;
    private final UsuarioMapper usuarioMapper;
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;

    public UsuarioController(CrearUsuarioUseCase crearUsuarioUseCase, UsuarioMapper usuarioMapper,ConsultarUsuarioUseCase consultarUsuarioUseCase) {
        this.crearUsuarioUseCase = crearUsuarioUseCase;
        this.usuarioMapper = usuarioMapper;
        this.consultarUsuarioUseCase= consultarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(
            @Valid @RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuario = crearUsuarioUseCase.ejecutar(
                usuarioRequest.cedula(),
                usuarioRequest.nombre(),
                usuarioRequest.apellido(),
                usuarioRequest.correo(),
                usuarioRequest.contraseña(),
                usuarioRequest.tipoUsuario()
        );

        UsuarioResponse response = usuarioMapper.toResponse(usuario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cedula}")
                .buildAndExpand(usuario.getCedula())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }


    @GetMapping("/{cedula}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable UUID cedula) {

        Usuario usuario =consultarUsuarioUseCase.ejecutar(cedula);

        return ResponseEntity.ok(usuarioMapper.toResponse(usuario));
    }
}