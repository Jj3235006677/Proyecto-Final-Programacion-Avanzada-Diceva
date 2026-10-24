
package com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase;

import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EliminarJuegoMesaUseCase {

    private final JuegoMesaRepository juegoMesaRepository;

    @Transactional
    public void eliminarLogicamente(UUID idJuegoEliminar) {
        juegoMesaRepository.eliminarLogicamente(idJuegoEliminar);
    }
}
