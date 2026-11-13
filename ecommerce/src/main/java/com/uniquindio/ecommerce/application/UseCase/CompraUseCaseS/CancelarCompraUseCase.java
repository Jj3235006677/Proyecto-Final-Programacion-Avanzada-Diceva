package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.DetalleCompra;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CancelarCompraUseCase {

    private final CompraRepository compraRepository;
    private final JuegoMesaRepository juegoMesaRepository;

    public CancelarCompraUseCase(CompraRepository compraRepository,
                                 JuegoMesaRepository juegoMesaRepository) {
        this.compraRepository = compraRepository;
        this.juegoMesaRepository = juegoMesaRepository;
    }

    @Transactional
    public Compra ejecutar(UUID compraId, String cedula, boolean esAdmin) {

        Compra compra = compraRepository.obtenerPorId(compraId)
                .orElseThrow(() -> new NoSuchElementException("La compra no existe"));

        compra.cancelar(cedula, esAdmin);

        // Las unidades vuelven al inventario
        for (DetalleCompra detalle : compra.getDetalles()) {
            juegoMesaRepository.obtenerPorId(detalle.idJuego()).ifPresent(juego -> {
                juego.devolverStock(detalle.cantidad());
                juegoMesaRepository.guardar(juego);
            });
        }

        compraRepository.guardar(compra);

        return compra;
    }
}