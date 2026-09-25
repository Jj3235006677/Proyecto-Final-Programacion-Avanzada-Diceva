package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;

import java.util.UUID;

public class JuegoMesa {

    private final UUID id;

    private String nombre;
    private String descripcion;
    private Precio precio;
    private int stock;
    private RangoJugadores rangoJugadores;
    private ComplejidadJuego complejidad;
    private String mecanicaDeJuego;
    private String tematicaDeJuego;
    private int edadRecomendada;
    private String imagenUrl;
    private boolean activo;

    private JuegoMesa(
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
            boolean activo) {
        if (stock<0){
            throw new ReglaDominioException("El stock no puede ser negativo");//invariante Uno el stock no puede ser negativo
        }
        if(edadRecomendada<0){
            throw new ReglaDominioException("La edad recomendada no puede ser menor a cero");//invariante dos la edad no puede ser negativa
        }
        if (nombre == null || nombre.isBlank()//invariante 3 ningun campo puede quedar vacio
                || descripcion == null || descripcion.isBlank()
                || precio == null
                || rangoJugadores == null
                || complejidad == null
                || mecanicaDeJuego == null || mecanicaDeJuego.isBlank()
                || tematicaDeJuego == null || tematicaDeJuego.isBlank()
                || imagenUrl == null || imagenUrl.isBlank()) {

            throw new ReglaDominioException(
                    "Los campos obligatorios del juego no pueden estar vacios"
            );
        }


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

    public static JuegoMesa crear(
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
            boolean activo) {

        return new JuegoMesa(
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
                activo
        );
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