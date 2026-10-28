package com.uniquindio.ecommerce.infrastructura.Persistence.jpaSpribootTest;

import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.RankingJuego;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
public class JuegoMesaRepositoryJpaTest {

    @Autowired
    private JuegoMesaRepository juegoMesaRepository;

    @Test
    public void deberiaConsultarElRanking() {
        List<RankingJuego> lista =
                juegoMesaRepository.obtenerRankingJuegos();

        assertEquals(8, lista.size());
        assertFalse(lista.isEmpty());
    }
}
