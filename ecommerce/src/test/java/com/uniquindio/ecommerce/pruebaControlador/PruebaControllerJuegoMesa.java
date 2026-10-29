
package com.uniquindio.ecommerce.pruebaControlador;

import com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.CrearJuegoMesaUseCase;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.infrastructure.rest.controller.JuegoMesaController;
import com.uniquindio.ecommerce.infrastructure.rest.mapper.JuegoMesaMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JuegoMesaController.class)
@AutoConfigureMockMvc(addFilters = false)
class PruebaControllerJuegoMesa {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JuegoMesaMapper juegoMesaMapper;

    @MockitoBean
    private CrearJuegoMesaUseCase crearJuegoMesaUseCase;

    // Dependencias adicionales que necesita el controlador
    @MockitoBean
    private com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.ConsultarJuegoMesaUseCase consultarJuegoMesaUseCase;

    @MockitoBean
    private com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.BuscarJuegosPaginadosUseCase buscarJuegosPaginadosUseCase;

    @MockitoBean
    private com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.ObtenerRankingJuegosUseCase obtenerRankingJuegosUseCase;

    @MockitoBean
    private com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.EliminarJuegoMesaUseCase eliminarJuegoMesaUseCase;


    @Test
    void deberiaResponder400CuandoSeRompeUnaReglaDeNegocio() throws Exception {

        when(crearJuegoMesaUseCase.ejecutar(
                any(), any(), any(), any(), any(),
                anyInt(), any(), any(), any(), any(),
                anyInt(), any(), any()
        )).thenThrow(new ReglaDominioException("El stock no puede ser negativo"));

        mockMvc.perform(post("/api/juegoMesa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "stock": -1
                                }
                                """))
                .andExpect(status().isBadRequest());
    }


    @Test
    void noDeberiaLlamarAlCasoDeUsoSiLosDatosSonInvalidos() throws Exception {

        mockMvc.perform(post("/api/juegoMesa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());

        verify(crearJuegoMesaUseCase, never()).ejecutar(
                any(), any(), any(), any(), any(),
                anyInt(), any(), any(), any(), any(),
                anyInt(), any(), any()
        );
    }




}
