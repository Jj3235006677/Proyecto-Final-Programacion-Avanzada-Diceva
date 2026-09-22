package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {
    @Test
    void no_Se_Puede_Dejar_Campos_Vacios(){
        assertThrows(ReglaDominioException.class,()-> new Usuario(
                UUID.randomUUID(),
                "Juan",
                "Buitrago",
                "",
                "123456",
                TipoUsuario.CLIENTE
        ));
    }
   @Test
   void aunque_Un_usuario_cambie_De_Tipo_sigue_Siendo_El_mismo_Entidad(){
        Usuario usuarioUno=new Usuario(
                UUID.randomUUID(),
                "Juan",
                "Buitrago",
                "juan1234@gmail.com",
                "123456",
                TipoUsuario.CLIENTE);
                usuarioUno.tipoUsuario =TipoUsuario.PROVEDOR;
                Usuario usuarioDos=usuarioUno;


                assertEquals(usuarioUno,usuarioDos);

   }
}
