package com.uniquindio.ecommerce.infrastructure.rest.controller;

import com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase.ConsultarUsuarioUseCase;
import com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase.CrearUsuarioUseCase;
import com.uniquindio.ecommerce.application.UseCase.UsuarioUseCase.RealizarLogueUseCase;
import com.uniquindio.ecommerce.application.dto.request.LoginRequest;
import com.uniquindio.ecommerce.application.dto.request.UsuarioRequest;
import com.uniquindio.ecommerce.application.dto.response.UsuarioResponse;
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
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;
    private final RealizarLogueUseCase realizarLogueUseCase;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(
            CrearUsuarioUseCase crearUsuarioUseCase,
            ConsultarUsuarioUseCase consultarUsuarioUseCase,
            RealizarLogueUseCase realizarLogueUseCase,
            UsuarioMapper usuarioMapper) {

        this.crearUsuarioUseCase = crearUsuarioUseCase;
        this.consultarUsuarioUseCase = consultarUsuarioUseCase;
        this.realizarLogueUseCase = realizarLogueUseCase;
        this.usuarioMapper = usuarioMapper;
    }

    // Registrar un usuario
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

    // Consultar un usuario por su cédula
    @GetMapping("/{cedula}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(
            @PathVariable UUID cedula) {

        Usuario usuario = consultarUsuarioUseCase.ejecutar(cedula);

        return ResponseEntity.ok(
                usuarioMapper.toResponse(usuario)
        );
    }


    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {

        Usuario usuario = realizarLogueUseCase.ejecutar(
                loginRequest.correo(),
                loginRequest.contraseña()
        );

        UsuarioResponse response = usuarioMapper.toResponse(usuario);

        return ResponseEntity
                .ok()
                .body(response);
    }
}