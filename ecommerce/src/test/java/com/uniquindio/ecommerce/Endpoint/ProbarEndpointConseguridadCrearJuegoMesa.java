package com.uniquindio.ecommerce.Endpoint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class ProbarEndpointConseguridadCrearJuegoMesa {
    //Esta prueba es con el endpint de /POST/api/juegoMesa ya que este solo deja crar a admin ya logueados
    @Autowired
    private MockMvc mockMvc;

    @Test
    void sinTokenDeberiaResponder401AlCrearJuegoMesa() throws Exception {
        //401 no se quien eres
        mockMvc.perform(post("/api/juegoMesa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "cedulaProveedor": "aaaaaaaa-1111-1111-1111-111111111111",
                            "nombre": "Catan",
                            "descripcion": "Juego de estrategia y comercio de recursos",
                            "precio": {
                                "precioProducto": 85000
                            },
                            "stock": 20,
                            "rangoJugadores": {
                                "maximoJugadores": 8,
                                "minimoJugadores": 3
                            },
                            "complejidad": "INTERMEDIA",
                            "mecanicaDeJuego": "Gestión de recursos",
                            "tematicaDeJuego": "Aventura y exploración",
                            "edadRecomendada": 10,
                            "imagenUrl": "https://ejemplo.com/catan.jpg",
                            "estado": "DISPONIBLE"
                        }
                        """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void conTokenDeberiaResponder403AlCrearJuegoMesaComoUser() throws Exception {
        //se quien eres pero no tienes permiso

        mockMvc.perform(post("/api/juegoMesa")
                        .with(jwt().authorities(
                                new SimpleGrantedAuthority("USER")
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void conTokenDeberiaResponder201AlCrearJuegoMesaComoAdmin()

            throws Exception {
        // 200 good buen funcionamiento
        mockMvc.perform(post("/api/juegoMesa")
                        .with(jwt().authorities(
                                new SimpleGrantedAuthority("ADMIN")
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "cedulaProveedor": "aaaaaaaa-1111-1111-1111-111111111111",
                            "nombre": "Catan",
                            "descripcion": "Juego de estrategia y comercio de recursos",
                            "precio": {
                                "precioProducto": 85000
                            },
                            "stock": 20,
                            "rangoJugadores": {
                                "maximoJugadores": 8,
                                "minimoJugadores": 3
                            },
                            "complejidad": "INTERMEDIA",
                            "mecanicaDeJuego": "Gestión de recursos",
                            "tematicaDeJuego": "Aventura y exploración",
                            "edadRecomendada": 10,
                            "imagenUrl": "https://ejemplo.com/catan.jpg",
                            "estado": "DISPONIBLE"
                        }
                        """))
                .andExpect(status().isCreated());
    }
}
