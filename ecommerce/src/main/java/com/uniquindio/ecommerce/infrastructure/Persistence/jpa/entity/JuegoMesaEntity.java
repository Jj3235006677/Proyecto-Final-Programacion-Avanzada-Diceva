package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity;

import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.EstadoJuego;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "juegos_mesa")
@Getter
@Setter
@NoArgsConstructor
public class JuegoMesaEntity {

    @Id
    private UUID id;

    @Column(name = "cedula_proveedor", nullable = false)
    private UUID cedulaProveedor;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Embedded
    private PrecioEmbeddable precio;

    @Column(nullable = false)
    private int stock;

    @Embedded
    private RangoJugadoresEmbeddable rangoJugadores;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplejidadJuego complejidad;

    @Column(name = "mecanica_de_juego", nullable = false)
    private String mecanicaDeJuego;

    @Column(name = "tematica_de_juego", nullable = false)
    private String tematicaDeJuego;

    @Column(name = "edad_recomendada", nullable = false)
    private int edadRecomendada;

    @Column(name = "imagen_url", nullable = false)
    private String imagenUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoJuego estado;


}