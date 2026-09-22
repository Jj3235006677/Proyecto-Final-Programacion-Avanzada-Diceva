package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;

import java.util.Objects;
import java.util.UUID;

public class Comentario {
    private final UUID idComentario;
    private UUID codigoJuegoMesa;
    private UUID usuarioId;
    private String nombre;
    private int calificacion;
    private String Comentario;


    public Comentario(UUID idComentario, UUID codigoJuegoMesa, UUID usuarioId, String nombre, int calificacion, String comentario) {
        
        if(calificacion>5){
            throw new ReglaDominioException("La calificacion no puede ser mayor a 5");
        }else if(calificacion<0){
            throw new ReglaDominioException("La calificacion no puede ser menor a cero");
        } else if (idComentario==null || codigoJuegoMesa==null || usuarioId==null){
            throw new ReglaDominioException("no se pueden dejar campos vacios ");
        }else if (nombre.isEmpty() || comentario.isEmpty() ){
            throw new ReglaDominioException("No se pueden dejar campos vacios");
        }
        this.idComentario = idComentario;
        this.codigoJuegoMesa = codigoJuegoMesa;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.calificacion = calificacion;
        Comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Comentario that)) return false;
        return calificacion == that.calificacion && Objects.equals(idComentario, that.idComentario) && Objects.equals(codigoJuegoMesa, that.codigoJuegoMesa) && Objects.equals(usuarioId, that.usuarioId) && Objects.equals(nombre, that.nombre) && Objects.equals(Comentario, that.Comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComentario, codigoJuegoMesa, usuarioId, nombre, calificacion, Comentario);
    }
}
