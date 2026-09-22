package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RagoJugadores;

import java.util.UUID;

public class JuegoMesa {

    private final UUID id;

    private String nombre;
    private String descripcion;
    private Precio precio;
    private int stock;
    private RagoJugadores rangoJugadores;
    private ComplejidadJuego complejidad;
    private String mecanicaDeJuego;
    private String tematicaDeJuego;
    private int edadRecomendada;
    private String imagenUrl;
    private boolean activo;

    public JuegoMesa(
            UUID id,
            String nombre,
            String descripcion,
            Precio precio,
            int stock,
            RagoJugadores rangoJugadores,
            ComplejidadJuego complejidad,
            String mecanicaDeJuego,
            String tematicaDeJuego,
            int edadRecomendada,
            String imagenUrl,
            boolean activo) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.rangoJugadores = rangoJugadores;
        this.complejidad = complejidad;
        this.mecanicaDeJuego = mecanicaDeJuego;
        this.tematicaDeJuego = tematicaDeJuego;
        this.edadRecomendada = edadRecomendada;
        this.imagenUrl = imagenUrl;
        this.activo = activo;
    }

    public int consultarStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof JuegoMesa)) return false;

        JuegoMesa juegoMesa = (JuegoMesa) o;

        return id.equals(juegoMesa.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}