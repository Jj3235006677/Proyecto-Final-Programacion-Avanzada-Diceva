package com.uniquindio.ecommerce.PruebaIntegracion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BuscarJuegoMesaPorNombre {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deberiaBuscarJuegosPorTexto() throws Exception {
        mockMvc.perform(get("/api/juegoMesa")
                        .param("texto", "Catan")
                        .param("pagina", "0")
                        .param("tamanio", "10"))
                .andExpect(status().isOk());
    }
}