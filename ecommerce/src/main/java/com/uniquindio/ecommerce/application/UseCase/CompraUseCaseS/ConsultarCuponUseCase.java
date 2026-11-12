package com.uniquindio.ecommerce.application.UseCase.CuponUseCase;

import com.uniquindio.ecommerce.domain.entity.Cupon;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.CuponRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConsultarCuponUseCase {

    private final CuponRepository cuponRepository;

    public ConsultarCuponUseCase(CuponRepository cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    public Cupon ejecutar(String codigo) {

        Cupon cupon = cuponRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new ReglaDominioException(
                        "El cupón " + codigo.toUpperCase() + " no existe"));

        cupon.verificarVigente(LocalDate.now());

        return cupon;
    }
}