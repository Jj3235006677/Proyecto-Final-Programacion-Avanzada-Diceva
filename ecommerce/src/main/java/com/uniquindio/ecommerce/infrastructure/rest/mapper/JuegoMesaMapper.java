package com.uniquindio.ecommerce.infrastructure.rest.mapper;

import com.uniquindio.ecommerce.application.dto.response.JuegoMesaResponse;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import org.springframework.stereotype.Component;

@Component
public class JuegoMesaMapper {

    public JuegoMesaResponse toResponse(JuegoMesa juegoMesa) {
        return new JuegoMesaResponse(
                juegoMesa.getId(),
                juegoMesa.getNombre(),
                juegoMesa.getDescripcion(),
                juegoMesa.getPrecio(),
                juegoMesa.getStock(),
                juegoMesa.getRangoJugadores(),
                juegoMesa.getComplejidad(),
                juegoMesa.getMecanicaDeJuego(),
                juegoMesa.getTematicaDeJuego(),
                juegoMesa.getEdadRecomendada(),
                juegoMesa.getImagenUrl(),
                juegoMesa.isActivo()
        );
    }
}