package com.uniquindio.ecommerce.application.UseCase.CompraUseCase;

import com.uniquindio.ecommerce.domain.Service.VerificadorDeCompra;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AgregarJuegoUseCase {
    private final CompraRepository compraRepository;
    private final JuegoMesaRepository juegoMesaRepository;
    private final VerificadorDeCompra verificadorDeCompra;



    @Transactional
    public void ejecutar(UUID idJuego,int cantidad,UUID idCompra){
        JuegoMesa juegoMesa=juegoMesaRepository.obtenerPorId(idJuego).orElseThrow();


        Compra compra=compraRepository.obtenerPorId(idCompra).orElseThrow();
        verificadorDeCompra.verificarQuePuedeAgregarJuego(compra,juegoMesa,cantidad);
       compra.agregarJuego(idJuego,cantidad,juegoMesa.getPrecio().precioProducto());
        juegoMesa.descontarStock(cantidad);

        compraRepository.guardar(compra);
        juegoMesaRepository.guardar(juegoMesa);
    }
}
