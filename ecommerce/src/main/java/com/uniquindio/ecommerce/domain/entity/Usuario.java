package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;

import java.util.UUID;

public class Usuario {

    private final UUID cedula;
    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String contraseña;
    private final TipoUsuario tipoUsuario;

    private Usuario(UUID cedula, String nombre, String apellido,
                    String correo, String contraseña, TipoUsuario tipoUsuario) {

        if (cedula == null) {
            throw new ReglaDominioException(
                    "No se puede crear un usuario sin cedula"
            );
        }

        if (nombre == null || nombre.isBlank()
                || apellido == null || apellido.isBlank()
                || correo == null || correo.isBlank()
                || contraseña == null || contraseña.isBlank()
                || tipoUsuario == null) {

            throw new ReglaDominioException(
                    "Los campos obligatorios del usuario no pueden estar vacios"
            );
        }

        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public static Usuario crear(UUID cedula, String nombre, String apellido,
                                String correo, String contraseña,
                                TipoUsuario tipoUsuario) {

        return new Usuario(
                cedula,
                nombre,
                apellido,
                correo,
                contraseña,
                tipoUsuario
        );
    }

    public UUID getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Usuario)) {
            return false;
        }

        Usuario usuario = (Usuario) o;

        return cedula.equals(usuario.cedula);
    }

    @Override
    public int hashCode() {
        return cedula.hashCode();
    }
}