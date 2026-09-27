package com.uniquindio.ecommerce.application.dto.request;

import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearJuegoMesaRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,

        @NotNull(message = "El precio es obligatorio")
        Precio precio,

        @NotNull(message = "El stock es obligatorio")
        Integer stock,

        @NotNull(message = "El rango de jugadores es obligatorio")
        RangoJugadores rangoJugadores,

        @NotNull(message = "La complejidad es obligatoria")
        ComplejidadJuego complejidad,

        @NotBlank(message = "La mecanica de juego es obligatoria")
        String mecanicaDeJuego,

        @NotBlank(message = "La tematica de juego es obligatoria")
        String tematicaDeJuego,

        @NotNull(message = "La edad recomendada es obligatoria")
        Integer edadRecomendada,

        @NotBlank(message = "La imagen es obligatoria")
        String imagenUrl
) {
}