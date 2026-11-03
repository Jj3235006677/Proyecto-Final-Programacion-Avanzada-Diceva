package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;

import java.util.UUID;

public class Comentario {

    public static final int MAX_CARACTERES_COMENTARIO = 500;
    public static final int MAX_CARACTERES_TITULO = 100;

    private final UUID idComentario;
    private final UUID idCompra;
    private final UUID codigoJuegoMesa;
    private final String cedula;
    private final String titulo;
    private final Calificacion calificacion;
    private final String comentario;
    private boolean eliminacionLogica;

    private Comentario(UUID idComentario, UUID idCompra, UUID codigoJuegoMesa, String cedula,
                       String titulo, Calificacion calificacion, String comentario,
                       boolean eliminacionLogica) {
        this.idComentario = idComentario;
        this.idCompra = idCompra;
        this.codigoJuegoMesa = codigoJuegoMesa;
        this.cedula = cedula;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.eliminacionLogica = eliminacionLogica;
    }

    public static Comentario crearComentario(UUID idComentario, UUID idCompra, UUID codigoJuegoMesa,
                                             String cedula, String titulo,
                                             Calificacion calificacion, String comentario) {
        validar(idComentario, idCompra, codigoJuegoMesa, cedula, titulo, calificacion, comentario);
        return new Comentario(idComentario, idCompra, codigoJuegoMesa, cedula,
                titulo, calificacion, comentario, false);
    }

    public static Comentario reconstruir(UUID idComentario, UUID idCompra, UUID codigoJuegoMesa,
                                         String cedula, String titulo, Calificacion calificacion,
                                         String comentario, boolean eliminacionLogica) {
        validar(idComentario, idCompra, codigoJuegoMesa, cedula, titulo, calificacion, comentario);
        return new Comentario(idComentario, idCompra, codigoJuegoMesa, cedula,
                titulo, calificacion, comentario, eliminacionLogica);
    }

    private static void validar(UUID idComentario, UUID idCompra, UUID codigoJuegoMesa,
                                String cedula, String titulo, Calificacion calificacion,
                                String comentario) {
        if (idComentario == null)
            throw new ReglaDominioException("El identificador del comentario es obligatorio");
        if (idCompra == null)
            throw new ReglaDominioException("Todo comentario debe estar asociado a una compra");
        if (codigoJuegoMesa == null)
            throw new ReglaDominioException("El identificador del juego de mesa es obligatorio");
        if (cedula == null || cedula.isBlank())
            throw new ReglaDominioException("El identificador del usuario es obligatorio");
        if (titulo == null || titulo.isBlank())
            throw new ReglaDominioException("El título del comentario es obligatorio");
        if (titulo.length() > MAX_CARACTERES_TITULO)
            throw new ReglaDominioException("El título no puede superar los 100 caracteres");
        if (calificacion == null)
            throw new ReglaDominioException("La calificación es obligatoria");
        if (comentario == null || comentario.isBlank())
            throw new ReglaDominioException("El contenido del comentario es obligatorio");
        if (comentario.length() > MAX_CARACTERES_COMENTARIO)
            throw new ReglaDominioException("El comentario no puede superar los 500 caracteres");
    }

    public void eliminarLogicamente() { this.eliminacionLogica = true; }

    public UUID getIdComentario() { return idComentario; }
    public UUID getIdCompra() { return idCompra; }
    public UUID getCodigoJuegoMesa() { return codigoJuegoMesa; }
    public String getCedula() { return cedula; }
    public String getTitulo() { return titulo; }
    public Calificacion getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }
    public boolean isEliminacionLogica() { return eliminacionLogica; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comentario otro)) return false;
        return idComentario.equals(otro.idComentario);
    }

    @Override
    public int hashCode() { return idComentario.hashCode(); }
}