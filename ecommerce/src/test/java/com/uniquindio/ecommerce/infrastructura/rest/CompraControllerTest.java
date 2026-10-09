package com.uniquindio.ecommerce.infrastructura.rest;

import com.uniquindio.ecommerce.application.UseCase.RealizarCompraUseCase;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.infrastructure.rest.controller.CompraController;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.CompraMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompraController.class)
class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RealizarCompraUseCase realizarCompraUseCase;

    @MockitoBean
    private CompraMapper mapper;

    @Test
    void deberiaCrearCompraCuandoDatosValidos() throws Exception {

        // Arrange
        String requestJson = """
    {
        "idListaJuegosMesa": [
            "550e8400-e29b-41d4-a716-446655440001"
        ],
        "cedulaUsuario": "550e8400-e29b-41d4-a716-446655440002",
        "precioTotal": {
            "precioProducto": 6000
        }
    }
    """;

        Compra compraSimulada = Compra.realizarCompra(
                UUID.fromString("550e8400-e29b-41d4-a716-446655440003"),
                List.of(UUID.fromString("550e8400-e29b-41d4-a716-446655440001")),
                UUID.fromString("550e8400-e29b-41d4-a716-446655440002"),
                new Precio(6000),
                LocalDateTime.now()
        );

        // Configurar el comportamiento del caso de uso
        when(realizarCompraUseCase.ejecutar(any(), any(), any(), any(),any()))
                .thenReturn(compraSimulada);

// ACT y ASSERT
        mockMvc.perform(post("/api/compras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}