package com.uniquindio.ecommerce.infrastructure.Persistence.Memoria;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.ResultadoPaginado;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public ResultadoPaginado<JuegoMesa> buscarPaginado(String texto, Integer precioMaximo, int pagina, int tamanio) {
        return null;
    }

}
