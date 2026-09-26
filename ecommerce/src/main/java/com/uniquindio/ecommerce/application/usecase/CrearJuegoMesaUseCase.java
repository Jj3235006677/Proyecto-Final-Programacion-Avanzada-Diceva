package com.uniquindio.ecommerce.application.usecase;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;

import java.util.UUID;

public class CrearJuegoMesaUseCase {

    private final JuegoMesaRepository repository;

    public CrearJuegoMesaUseCase(JuegoMesaRepository repository){
        this.repository=repository;
    }

    public JuegoMesa ejecutar(UUID id, String nombre, String descripcion, Precio precio,
                              int stock, RangoJugadores rangoJugadores, ComplejidadJuego complejidad,
                              String mecanicaDeJuego, String tematicaDeJuego, int edadRecomendada,
                              String imagenUrl, boolean activo){
        
        JuegoMesa juegoMesa=JuegoMesa.crear(id,nombre,descripcion,precio,stock,rangoJugadores,complejidad,mecanicaDeJuego,tematicaDeJuego,edadRecomendada,imagenUrl,activo);
        repository.guardar(juegoMesa);
        return juegoMesa;
    }
}
