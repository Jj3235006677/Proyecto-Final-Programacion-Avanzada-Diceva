package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.EstadoJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;

import java.util.UUID;

public class JuegoMesa {

    private final UUID id;
    private final UUID cedulaProveedor;
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
    private EstadoJuego estado;

    private JuegoMesa(
            UUID cedulaProveedor,
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
            EstadoJuego estado) {

        this.cedulaProveedor = cedulaProveedor;
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
        this.estado = estado;
    }

    private static void validarDatosJuego(
            UUID cedulaProveedor,
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
            EstadoJuego estado) {

        if (cedulaProveedor == null) {
            throw new ReglaDominioException(
                    "El identificador del proveedor es obligatorio"
            );
        }

        if (id == null) {
            throw new ReglaDominioException(
                    "El identificador del juego es obligatorio"
            );
        }

        if (stock < 0) {
            throw new ReglaDominioException(
                    "El stock no puede ser negativo"
            );
        }

        if (edadRecomendada < 0) {
            throw new ReglaDominioException(
                    "La edad recomendada no puede ser menor a cero"
            );
        }

        if (nombre == null || nombre.isBlank()
                || descripcion == null || descripcion.isBlank()
                || precio == null
                || rangoJugadores == null
                || complejidad == null
                || mecanicaDeJuego == null || mecanicaDeJuego.isBlank()
                || tematicaDeJuego == null || tematicaDeJuego.isBlank()
                || imagenUrl == null || imagenUrl.isBlank()
                || estado == null) {

            throw new ReglaDominioException(
                    "Los campos obligatorios del juego no pueden estar vacíos"
            );
        }
    }

    public static JuegoMesa crear(
            UUID cedulaProveedor,
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
            EstadoJuego estado) {

        validarDatosJuego(
                cedulaProveedor,
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
                estado
        );

        return new JuegoMesa(
                cedulaProveedor,
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
                estado
        );
    }

    public void descontarStock(int cantidad) {

        if (cantidad <= 0) {
            throw new ReglaDominioException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        if (cantidad > stock) {
            throw new ReglaDominioException(
                    "No hay stock suficiente para cubrir el pedido"
            );
        }

        stock -= cantidad;

        if (stock == 0) {
            estado = EstadoJuego.AGOTADO;
        }
    }

    public void devolverStock(int cantidad) {

        if (cantidad <= 0) {
            throw new ReglaDominioException(
                    "La cantidad a devolver debe ser mayor que cero"
            );
        }

        try {
            stock = Math.addExact(stock, cantidad);
        } catch (ArithmeticException e) {
            throw new ReglaDominioException(
                    "El stock excede el límite permitido"
            );
        }

        if (stock > 0 && estado == EstadoJuego.AGOTADO) {
            estado = EstadoJuego.DISPONIBLE;
        }
    }

    public UUID getId() {
        return id;
    }

    public int consultarStock() {
        return stock;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Precio getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public RangoJugadores getRangoJugadores() {
        return rangoJugadores;
    }

    public ComplejidadJuego getComplejidad() {
        return complejidad;
    }

    public String getMecanicaDeJuego() {
        return mecanicaDeJuego;
    }

    public String getTematicaDeJuego() {
        return tematicaDeJuego;
    }

    public int getEdadRecomendada() {
        return edadRecomendada;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public EstadoJuego getEstado() {
        return estado;
    }

    public UUID getCedulaProveedor() {
        return cedulaProveedor;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof JuegoMesa)) {
            return false;
        }

        JuegoMesa juegoMesa = (JuegoMesa) o;

        return id.equals(juegoMesa.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}