package com.uniquindio.ecommerce.application.dto.request;

import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.EstadoJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record JuegoMesaRequest(
        @NotNull(message = "La cédula del proveedor es obligatoria")
        UUID cedulaProveedor,

        @NotBlank(message = "El nombre del juego es obligatorio")
        String nombre,

        @NotBlank(message = "La descripción del juego es obligatoria")
        String descripcion,

        @NotNull(message = "El precio es obligatorio")
        Precio precio,

        @Min(value = 0, message = "El stock no puede ser negativo")
        int stock,

        @NotNull(message = "El rango de jugadores es obligatorio")
        RangoJugadores rangoJugadores,

        @NotNull(message = "La complejidad del juego es obligatoria")
        ComplejidadJuego complejidad,

        @NotBlank(message = "La mecánica de juego es obligatoria")
        String mecanicaDeJuego,

        @NotBlank(message = "La temática del juego es obligatoria")
        String tematicaDeJuego,

        @Min(value = 0, message = "La edad recomendada no puede ser negativa")
        int edadRecomendada,

        @NotBlank(message = "La URL de la imagen es obligatoria")
        String imagenUrl,

        EstadoJuego estado



) {
}