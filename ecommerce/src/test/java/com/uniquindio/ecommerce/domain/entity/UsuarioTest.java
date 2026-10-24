
package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsuarioTest {

    @Test
    void noDebePermitirCamposObligatoriosVacios() {
        UUID cedula = UUID.randomUUID();

        assertThrows(
                ReglaDominioException.class,
                () -> Usuario.crear(
                        cedula,
                        "Juan",
                        "Buitrago",
                        "",
                        "123456",
                        TipoUsuario.USER
                )
        );
    }

    @Test
    void noDebeCrearUsuarioSinCedula() {
        assertThrows(
                ReglaDominioException.class,
                () -> Usuario.crear(
                        null,
                        "Juan",
                        "Buitrago",
                        "juan1234@gmail.com",
                        "123456",
                        TipoUsuario.USER
                )
        );
    }

    @Test
    void usuariosConLaMismaCedulaDebenSerIguales() {
        UUID cedula = UUID.randomUUID();

        Usuario usuarioUno = Usuario.crear(
                cedula,
                "Juan",
                "Buitrago",
                "juan1234@gmail.com",
                "123456",
                TipoUsuario.USER
        );

        Usuario usuarioDos = Usuario.crear(
                cedula,
                "Pedro",
                "Perez",
                "pedro@gmail.com",
                "654321",
                TipoUsuario.ADMIN
        );

        assertEquals(usuarioUno, usuarioDos);
    }

    @Test
    void usuariosConDiferenteCedulaDebenSerDiferentes() {
        Usuario usuarioUno = Usuario.crear(
                UUID.randomUUID(),
                "Juan",
                "Buitrago",
                "juan1234@gmail.com",
                "123456",
                TipoUsuario.USER
        );

        Usuario usuarioDos = Usuario.crear(
                UUID.randomUUID(),
                "Juan",
                "Buitrago",
                "juan1234@gmail.com",
                "123456",
                TipoUsuario.USER
        );

        assertNotEquals(usuarioUno, usuarioDos);
    }
}
