
package com.uniquindio.ecommerce.DataJpaTest;

import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.RankingJuego;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.Mapper.JuegoMesaPersistenceMapper;
import com.uniquindio.ecommerce.infrastructure.Persistence.jpa.repositoryJpa.JuegoMesaRepositorioJpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({
        JuegoMesaRepositorioJpa.class,
        JuegoMesaPersistenceMapper.class
})
class RankingJuegoRepositoryDataJpaTest {
    //Arrage
    @Autowired
    private JuegoMesaRepository repository;

    @Test
    void deberiaObtenerRankingDeJuegos() {
        // Act
        List<RankingJuego> ranking = repository.obtenerRankingJuegos();

        // Assert
        assertNotNull(ranking);
        assertEquals(8, ranking.size());
    }
}
