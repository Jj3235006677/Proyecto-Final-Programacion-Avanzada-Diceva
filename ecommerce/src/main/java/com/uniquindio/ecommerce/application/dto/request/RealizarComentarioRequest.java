package com.uniquindio.ecommerce.application.dto.request;

import com.uniquindio.ecommerce.domain.valueobject.Calificacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RealizarComentarioRequest(

        @NotNull(message = "El juego de mesa es obligatorio")
        UUID codigoJuegoMesa,

        @NotNull(message = "El usuario es obligatorio")
        UUID usuarioId,

        @NotBlank(message = "El titulo es obligatorio")
        String titulo,

        @NotNull(message = "La calificacion es obligatoria")
        Calificacion calificacion,

        @NotBlank(message = "El comentario es obligatorio")
        String comentario

) {
}