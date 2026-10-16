package com.uniquindio.ecommerce.application.UseCase;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class AgregarJuegoUseCase {
    private final CompraRepository compraRepository;
    private final JuegoMesaRepository juegoMesaRepository;

    public AgregarJuegoUseCase(CompraRepository compraRepository, JuegoMesaRepository juegoMesaRepository) {
        this.compraRepository = compraRepository;
        this.juegoMesaRepository = juegoMesaRepository;
    }

    public void ejecutar(UUID idJuego,int cantidad,UUID idCompra){
       Compra compra=compraRepository.obtenerPorId(idCompra).orElseThrow();
       compra.agregarJuego(idJuego,cantidad);
        JuegoMesa juegoMesa=juegoMesaRepository.obtenerPorId(idJuego).orElseThrow();
        juegoMesa.descontarStock(cantidad);

        compraRepository.guardar(compra);
        juegoMesaRepository.guardar(juegoMesa);
    }
}
