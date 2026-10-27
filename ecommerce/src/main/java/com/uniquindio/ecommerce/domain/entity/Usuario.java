
package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;

import java.util.Objects;

public class Usuario {

    private final String id;
    private final String email;
    private final String cedula;
    private final String password;
    private final TipoUsuario rol;

    private Usuario(
            String id,
            String email,
            String cedula,
            String password,
            TipoUsuario rol) {

        this.id = id;
        this.email = email;
        this.cedula = cedula;
        this.password = password;
        this.rol = rol;
    }

    public static Usuario crear(
            String id,
            String email,
            String cedula,
            String password,
            TipoUsuario rol) {

        validarDatos(id, email, cedula, password, rol);

        return new Usuario(id, email, cedula, password, rol);
    }

    private static void validarDatos(
            String id,
            String email,
            String cedula,
            String password,
            TipoUsuario rol) {

        if (id == null || id.isBlank()) {
            throw new ReglaDominioException(
                    "El identificador del usuario es obligatorio");
        }

        if (email == null || email.isBlank()
                || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new ReglaDominioException(
                    "El correo del usuario no es válido");
        }

        if (cedula == null || cedula.isBlank()) {
            throw new ReglaDominioException(
                    "La cédula del usuario es obligatoria");
        }

        if (password == null || password.isBlank()) {
            throw new ReglaDominioException(
                    "La contraseña del usuario es obligatoria");
        }

        if (rol == null) {
            throw new ReglaDominioException(
                    "El rol del usuario es obligatorio");
        }
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCedula() {
        return cedula;
    }

    public String getPassword() {
        return password;
    }

    public TipoUsuario getRol() {
        return rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Usuario usuario)) {
            return false;
        }

        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
