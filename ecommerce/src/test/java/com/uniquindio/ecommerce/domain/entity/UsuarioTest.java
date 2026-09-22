package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {
    @Test
    void no_Se_Puede_Dejar_Campos_Vacios(){


        assertThrows(ReglaDominioException.class,()-> new Usuario(
                UUID.randomUUID(),
                "",
                "Buitrago",
                "juan@gmail.com",
                "123456",
                TipoUsuario.CLIENTE
        ));
    }
}
