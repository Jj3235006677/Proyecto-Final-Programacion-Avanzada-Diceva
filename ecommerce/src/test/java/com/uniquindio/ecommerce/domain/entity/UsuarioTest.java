package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {

    @Test
    void No_Se_Puede_Dejar_Campos_Vacios() {

        // Arrange
        UUID cedula = UUID.randomUUID();

        // Act & Assert
        assertThrows(
                ReglaDominioException.class,
                () -> Usuario.crear(
                        cedula,
                        "Juan",
                        "Buitrago",
                        "",
                        "123456",
                        TipoUsuario.CLIENTE
                )
        );
    }

    @Test
    void No_Se_Puede_Crear_Un_Usuario_Sin_Cedula() {

        // Arrange
        UUID cedula = null;

        // Act & Assert
        assertThrows(
                ReglaDominioException.class,
                () -> Usuario.crear(
                        cedula,
                        "Juan",
                        "Buitrago",
                        "juan1234@gmail.com",
                        "123456",
                        TipoUsuario.CLIENTE
                )
        );
    }

    @Test
    void Dos_Usuarios_Con_La_Misma_Cedula_Son_La_Misma_Entidad() {

        // Arrange
        UUID cedula = UUID.randomUUID();

        Usuario usuarioUno = Usuario.crear(
                cedula,
                "Juan",
                "Buitrago",
                "juan1234@gmail.com",
                "123456",
                TipoUsuario.CLIENTE
        );

        Usuario usuarioDos = Usuario.crear(
                cedula,
                "Pedro",
                "Perez",
                "pedro@gmail.com",
                "654321",
                TipoUsuario.PROVEEDOR
        );

        // Act & Assert
        assertEquals(usuarioUno, usuarioDos);
    }

    @Test
    void Dos_Usuarios_Con_Diferente_Cedula_Son_Entidades_Diferentes() {

        // Arrange
        Usuario usuarioUno = Usuario.crear(
                UUID.randomUUID(),
                "Juan",
                "Buitrago",
                "juan1234@gmail.com",
                "123456",
                TipoUsuario.CLIENTE
        );

        Usuario usuarioDos = Usuario.crear(
                UUID.randomUUID(),
                "Juan",
                "Buitrago",
                "juan1234@gmail.com",
                "123456",
                TipoUsuario.CLIENTE
        );

        // Act & Assert
        assertNotEquals(usuarioUno, usuarioDos);
    }
}