package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity;

import com.uniquindio.ecommerce.domain.valueobject.Calificacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
public class ComentarioEntity {

    @Id
    @Column(name = "id_comentario", nullable = false)
    private UUID idComentario;

    @Column(name = "codigo_juego_mesa", nullable = false)
    private UUID codigoJuegoMesa;

    @Column(name = "cedula_usuario", nullable = false)
    private String cedula;

    @Column(name = "titulo", nullable = false)
    private String titulo;


    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "comentario", nullable = false, length = 2000)
    private String comentario;

    @Column(name = "eliminacion_logica", nullable = false)
    private boolean eliminacionLogica = false;
}