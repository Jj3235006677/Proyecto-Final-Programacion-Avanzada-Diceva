package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.Calificacion;


import java.util.UUID;

public class Comentario {
    private final UUID idComentario;
    private UUID codigoJuegoMesa;
    private UUID usuarioId;
    private String titulo;
    private Calificacion calificacion;
    private String comentario;


    private Comentario(UUID idComentario, UUID codigoJuegoMesa,
                       UUID usuarioId, String titulo, Calificacion calificacion, String comentario) {
        
        if (idComentario==null || codigoJuegoMesa==null || usuarioId==null){
            throw new ReglaDominioException("no se pueden dejar campos vacios ");
        }
        if (calificacion == null) {
            throw new ReglaDominioException(
                    "La calificacion no puede ser nula"
            );
        }

        if (titulo == null || titulo.isBlank() || comentario == null || comentario.isBlank()) {
            throw new ReglaDominioException(
                    "El Titulo y el comentario no pueden estar vacios"
            );
        }
        this.idComentario = idComentario;
        this.codigoJuegoMesa = codigoJuegoMesa;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }
    public static Comentario crearComentario(UUID idComentario, UUID codigoJuegoMesa, UUID usuarioId,
                                             String titulo, Calificacion calificacion, String comentario){
        return new Comentario(idComentario,codigoJuegoMesa,usuarioId,titulo,calificacion,comentario);

    }

    public UUID getIdComentario() {
        return idComentario;
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
