package com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.EstadoJuego;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CrearJuegoMesaUseCase {

    private final JuegoMesaRepository repository;

    public CrearJuegoMesaUseCase(JuegoMesaRepository repository){
        this.repository=repository;
    }

    public JuegoMesa ejecutar(UUID cedulaProveedor,UUID id, String nombre, String descripcion, Precio precio,
                              int stock, RangoJugadores rangoJugadores, ComplejidadJuego complejidad,
                              String mecanicaDeJuego, String tematicaDeJuego, int edadRecomendada,
                              String imagenUrl, EstadoJuego estado){

        JuegoMesa juegoMesa=JuegoMesa.crear( cedulaProveedor,id,nombre,descripcion,precio,stock,rangoJugadores,complejidad,mecanicaDeJuego,tematicaDeJuego,edadRecomendada,imagenUrl,estado);
        repository.guardar(juegoMesa);
        return juegoMesa;
    }
}
