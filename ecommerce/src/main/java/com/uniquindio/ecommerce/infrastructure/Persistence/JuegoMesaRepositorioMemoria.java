package com.uniquindio.ecommerce.infrastructure.Persistence;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Repository
public class JuegoMesaRepositorioMemoria implements JuegoMesaRepository {

    private final Map<UUID, JuegoMesa> juegosMesa=new HashMap<>();







    @Override
    public Optional<JuegoMesa> obtenerPorId(UUID id) {
        return Optional.ofNullable(juegosMesa.get(id));
    }
    @Override
    public void guardar(JuegoMesa juegoMesa){
        juegosMesa.put(juegoMesa.getId(),juegoMesa);
    }

}
