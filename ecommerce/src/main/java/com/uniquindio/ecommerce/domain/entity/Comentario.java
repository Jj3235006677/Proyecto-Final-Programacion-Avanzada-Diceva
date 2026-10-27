
package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;

import java.util.UUID;

public class Comentario {

    private final UUID idComentario;
    private final UUID codigoJuegoMesa;
    private final String cedula;
    private final String titulo;
    private final Calificacion calificacion;
    private final String comentario;
    private boolean eliminacionLogica;

    private Comentario(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String cedula,
            String titulo,
            Calificacion calificacion,
            String comentario) {

        this.idComentario = idComentario;
        this.codigoJuegoMesa = codigoJuegoMesa;
        this.cedula = cedula;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.eliminacionLogica = false;
    }

    private Comentario(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String cedula,
            String titulo,
            Calificacion calificacion,
            String comentario,
            boolean eliminacionLogica) {

        this.idComentario = idComentario;
        this.codigoJuegoMesa = codigoJuegoMesa;
        this.cedula = cedula;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.eliminacionLogica = eliminacionLogica;
    }

    private static void validarDatosComentario(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String cedula,
            String titulo,
            Calificacion calificacion,
            String comentario) {

        if (idComentario == null) {
            throw new ReglaDominioException(
                    "El identificador del comentario es obligatorio"
            );
        }

        if (codigoJuegoMesa == null) {
            throw new ReglaDominioException(
                    "El identificador del juego de mesa es obligatorio"
            );
        }

        if (cedula == null || cedula.isBlank()) {
            throw new ReglaDominioException(
                    "El identificador del usuario es obligatorio"
            );
        }

        if (titulo == null || titulo.isBlank()) {
            throw new ReglaDominioException(
                    "El título del comentario es obligatorio"
            );
        }

        if (calificacion == null) {
            throw new ReglaDominioException(
                    "La calificación no puede ser nula"
            );
        }

        if (comentario == null || comentario.isBlank()) {
            throw new ReglaDominioException(
                    "El contenido del comentario es obligatorio"
            );
        }
    }

    private static void validarDatosReconstruccion(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String cedula,
            String titulo,
            Calificacion calificacion,
            String comentario) {

        validarDatosComentario(
                idComentario,
                codigoJuegoMesa,
                cedula,
                titulo,
                calificacion,
                comentario
        );
    }

    public static Comentario crearComentario(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String usuarioId,
            String titulo,
            Calificacion calificacion,
            String comentario) {

        validarDatosComentario(
                idComentario,
                codigoJuegoMesa,
                usuarioId,
                titulo,
                calificacion,
                comentario
        );

        return new Comentario(
                idComentario,
                codigoJuegoMesa,
                usuarioId,
                titulo,
                calificacion,
                comentario
        );
    }

    public static Comentario reconstruir(
            UUID idComentario,
            UUID codigoJuegoMesa,
            String cedula,
            String titulo,
            Calificacion calificacion,
            String comentario,
            boolean eliminacionLogica) {

        validarDatosReconstruccion(
                idComentario,
                codigoJuegoMesa,
                cedula,
                titulo,
                calificacion,
                comentario
        );

        return new Comentario(
                idComentario,
                codigoJuegoMesa,
                cedula,
                titulo,
                calificacion,
                comentario,
                eliminacionLogica
        );
    }

    public void eliminarLogicamente() {
        this.eliminacionLogica = true;
    }

    public UUID getIdComentario() {
        return idComentario;
    }

    public UUID getCodigoJuegoMesa() {
        return codigoJuegoMesa;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTitulo() {
        return titulo;
    }

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean isEliminacionLogica() {
        return eliminacionLogica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comentario)) return false;

        Comentario comentario = (Comentario) o;
        return idComentario.equals(comentario.idComentario);
    }

    @Override
    public int hashCode() {
        return idComentario.hashCode();
    }
}
