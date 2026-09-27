package com.uniquindio.ecommerce.domain.TestUseCase;

import com.uniquindio.ecommerce.application.RealizarComentarioUseCase;
import com.uniquindio.ecommerce.domain.entity.Comentario;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;
import com.uniquindio.ecommerce.infrastructure.ComentarioRepositorioEnMemoria;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RealizarComentarioUseCaseTest {

    @Test
    public void Debe_Crear_Y_Guardar_Un_Comentario() {

        // Arrange
        ComentarioRepositorioEnMemoria repository = new ComentarioRepositorioEnMemoria();//primero creamos donde alojar el comentario

        RealizarComentarioUseCase useCase = new RealizarComentarioUseCase(repository);//creamos el caso de uso y le pasa

        UUID idComentario = UUID.randomUUID();
        UUID codigoJuegoMesa = UUID.randomUUID();
        UUID usuarioId = UUID.randomUUID();

        // Act
        Comentario comentario = useCase.ejecutar(//creamos el objeto comentario
                idComentario,
                codigoJuegoMesa,
                usuarioId,
                "Excelente juego",
                new Calificacion(5),
                "Me gustó mucho el juego"
        );

        // Assert
        assertNotNull(comentario);
        assertEquals(
                comentario,
                repository.obtenerComentariId(idComentario).orElse(null)
        );
    }
}