package com.uniquindio.ecommerce.infrastructura.Persistence.jpaSpribootTest;

import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.valueobject.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CompraRepositoryJpaTest {


    @Autowired
    private CompraRepository compraRepository;


    @Test
    void deberiaGuardarYRecuperarUnaCompraConSuPrecioYEstado(){
        //Arrange preparamos todo para la prueba


        UUID idCompra = UUID.randomUUID();
        UUID cedulaUsuario = UUID.randomUUID();
        List<UUID> list=new ArrayList<>();
        Compra compra = Compra.realizarCompra(idCompra, list, cedulaUsuario, LocalDateTime.now());
        UUID idJuego = UUID.fromString(
                "aaaaaaaa-1111-1111-1111-111111111111"
        );

        UUID cedulaProveedor = UUID.randomUUID();

        JuegoMesa juego = JuegoMesa.crear(
                cedulaProveedor,
                idJuego,
                "Catan",
                "Juego de estrategia y comercio de recursos",
                new Precio(85000),
                20,
                new RangoJugadores(8,3),
                ComplejidadJuego.INTERMEDIA,
                "Gestión de recursos",
                "Aventura y exploración",
                10,
                "https://ejemplo.com/catan.jpg",
                EstadoJuego.DISPONIBLE
        );

        compra.agregarJuego(idJuego,5,juego.getPrecio().precioProducto());





        //Act Realizamos la prueba
        compraRepository.guardar(compra);
        Compra compraRecuperada=compraRepository.obtenerPorId(idCompra).orElseThrow();


        //Assert Compramos los datos
        assertEquals(idCompra, compraRecuperada.getId());
        assertEquals(425000, compraRecuperada.getPrecioTotal().precioProducto());
        assertEquals(EstadoPago.PENDIENTE, compraRecuperada.consultarEstadoCompra());




    }


}
