package com.uniquindio.ecommerce.application.dto.response;

import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;

import java.util.UUID;

public record JuegoMesaResponse(

        UUID id,
        String nombre,
        String descripcion,
        Precio precio,
        int stock,
        RangoJugadores rangoJugadores,
        ComplejidadJuego complejidad,
        String mecanicaDeJuego,
        String tematicaDeJuego,
        int edadRecomendada,
        String imagenUrl,
        boolean activo

) {
}