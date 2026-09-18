package com.uniquindio.ecommerce.application;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RagoJugadores;

public class RegistrarJuego {

    // Caso de uso: registrar un nuevo juego de mesa.

    public JuegoMesa ejecutar(
            int id,
            String nombre,
            String descripcion,
            Precio precio,
            int stock,
            RagoJugadores rangoJugadores,
            ComplejidadJuego complejidad,
            String mecanicaDeJuego,
            String tematicaDeJuego,
            int edadRecomendada,
            String imagenUrl) {

        // Crear el juego de mesa con la información recibida.
        JuegoMesa juego = new JuegoMesa(
                id,
                nombre,
                descripcion,
                precio,
                stock,
                rangoJugadores,
                complejidad,
                mecanicaDeJuego,
                tematicaDeJuego,
                edadRecomendada,
                imagenUrl,
                true
        );

        // Retornar el juego registrado.
        return juego;
    }
}