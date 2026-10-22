package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.JuegoMesaEntity;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity.PrecioEmbeddable;
import org.springframework.stereotype.Component;

@Component
public class JuegoMesaPersistenceMapper {

    /**
     * Convierte un juego de mesa del dominio
     * en una entidad que JPA puede guardar.
     */
    public JuegoMesaEntity toEntity(JuegoMesa juegoMesa) {

        JuegoMesaEntity entity = new JuegoMesaEntity();

        entity.setId(juegoMesa.getId());
        entity.setCedulaProveedor(juegoMesa.getCedulaProveedor());
        entity.setNombre(juegoMesa.getNombre());
        entity.setDescripcion(juegoMesa.getDescripcion());

        entity.setPrecio(
                new PrecioEmbeddable(
                        juegoMesa.getPrecio().precioProducto()
                )
        );

        entity.setStock(juegoMesa.getStock());
        entity.setRangoJugadores(juegoMesa.getRangoJugadores());
        entity.setComplejidad(juegoMesa.getComplejidad());
        entity.setMecanicaDeJuego(juegoMesa.getMecanicaDeJuego());
        entity.setTematicaDeJuego(juegoMesa.getTematicaDeJuego());
        entity.setEdadRecomendada(juegoMesa.getEdadRecomendada());
        entity.setImagenUrl(juegoMesa.getImagenUrl());
        entity.setEstado(juegoMesa.getEstado());

        return entity;
    }

    /**
     * Reconstruye un juego del dominio
     * a partir de los datos recuperados de JPA.
     */
    public JuegoMesa toDomain(JuegoMesaEntity entity) {

        return JuegoMesa.crear(
                entity.getCedulaProveedor(),
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                new Precio(entity.getPrecio().getMonto()),
                entity.getStock(),
                entity.getRangoJugadores(),
                entity.getComplejidad(),
                entity.getMecanicaDeJuego(),
                entity.getTematicaDeJuego(),
                entity.getEdadRecomendada(),
                entity.getImagenUrl(),
                entity.getEstado()
        );
    }
}