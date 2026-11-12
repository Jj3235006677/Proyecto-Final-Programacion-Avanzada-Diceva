package com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.Cupon;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.repository.CuponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AplicarCuponUseCase {

    private final CompraRepository compraRepository;
    private final CuponRepository cuponRepository;

    public AplicarCuponUseCase(CompraRepository compraRepository,
                               CuponRepository cuponRepository) {
        this.compraRepository = compraRepository;
        this.cuponRepository = cuponRepository;
    }

    @Transactional
    public Compra ejecutar(UUID compraId, String codigoCupon, String cedula) {

        Compra compra = compraRepository.obtenerPorId(compraId).orElseThrow();

        if (!compra.getCedulaUsuario().equals(cedula)) {
            throw new ReglaDominioException("Solo puedes usar cupones en tus propias compras");
        }

        Cupon cupon = cuponRepository.buscarPorCodigo(codigoCupon)
                .orElseThrow(() -> new ReglaDominioException(
                        "El cupón " + codigoCupon + " no existe"));

        compra.aplicarCupon(cupon, LocalDate.now());
        compraRepository.guardar(compra);

        return compra;
    }
}