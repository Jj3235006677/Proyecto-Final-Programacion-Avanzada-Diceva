package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import com.uniquindio.ecommerce.domain.Service.VerificadorDeCompra;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import jakarta.transaction.Transactional;
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
    public void ejecutar(UUID idJuego, int cantidad, UUID idCompra) {


        JuegoMesa juegoMesa = juegoMesaRepository.obtenerPorId(idJuego)
                .orElseThrow(() ->
                        new java.util.NoSuchElementException(
                                "El juego de mesa no existe"
                        )
                );


        Compra compra = compraRepository.obtenerPorId(idCompra)
                .orElseThrow(() ->
                        new java.util.NoSuchElementException(
                                "La compra no existe"
                        )
                );

        // 3. Verificar las reglas del negocio.
        verificadorDeCompra.verificarQuePuedeAgregarJuego(
                compra,
                juegoMesa,
                cantidad
        );

        // 4. Agregar los juegos a la compra.
        compra.agregarJuego(
                idJuego,
                cantidad,
                juegoMesa.getPrecio().precioProducto()
        );

        // 5. Descontar las existencias.
        juegoMesa.descontarStock(cantidad);

        // 6. Guardar los cambios.
        compraRepository.guardar(compra);
        juegoMesaRepository.guardar(juegoMesa);
    }
}