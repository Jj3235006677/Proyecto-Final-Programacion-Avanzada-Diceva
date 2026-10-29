package com.uniquindio.ecommerce.casosUsoTest;

import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.CrearCompraUseCase;
import com.uniquindio.ecommerce.application.UseCase.CompraUseCaseS.RealizarRembolsoUseCase;
import com.uniquindio.ecommerce.application.UseCase.JuegoMesaUseCase.CrearJuegoMesaUseCase;
import com.uniquindio.ecommerce.domain.entity.Compra;
import com.uniquindio.ecommerce.domain.entity.JuegoMesa;
import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.repository.CompraRepository;
import com.uniquindio.ecommerce.domain.repository.JuegoMesaRepository;
import com.uniquindio.ecommerce.domain.valueobject.ComplejidadJuego;
import com.uniquindio.ecommerce.domain.valueobject.EstadoJuego;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;
import com.uniquindio.ecommerce.domain.valueobject.RangoJugadores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CasosDeUsoTest {

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private JuegoMesaRepository juegoMesaRepository;

    private CrearCompraUseCase crearCompra;
    private RealizarRembolsoUseCase realizarReembolso;
    private CrearJuegoMesaUseCase crearJuegoMesa;

    @BeforeEach
    void preparar() {
        crearCompra = new CrearCompraUseCase(compraRepository);
        realizarReembolso = new RealizarRembolsoUseCase(compraRepository);
        crearJuegoMesa = new CrearJuegoMesaUseCase(juegoMesaRepository);
    }

    // ---------- CrearCompraUseCase (comprador) ----------

    @Test
    void crearCompraDeberiaGuardarYDevolverUnaCompraPendiente() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        Compra compra = crearCompra.ejecutar(id, List.of(UUID.randomUUID()),
                UUID.randomUUID(), LocalDateTime.now());

        // Assert
        assertEquals(EstadoPago.PENDIENTE, compra.consultarEstadoCompra());
        verify(compraRepository).guardar(compra);
    }

    @Test
    void crearCompraNoDeberiaGuardarSiFaltaElUsuario() {
        // Act + Assert: la regla la lanza el dominio, no el caso de uso
        assertThrows(ReglaDominioException.class, () ->
                crearCompra.ejecutar(UUID.randomUUID(), List.of(UUID.randomUUID()),
                        null, LocalDateTime.now()));

        verify(compraRepository, never()).guardar(any());
    }

    // ---------- RealizarRembolsoUseCase (comprador) ----------

    @Test
    void reembolsoDeberiaCambiarElEstadoYGuardarCuandoLaCompraEstaAprobada() {
        // Arrange
        UUID idCompra = UUID.randomUUID();

        Compra compra = Compra.realizarCompra(idCompra, List.of(UUID.randomUUID()),
                UUID.randomUUID(), LocalDateTime.now());
        compra.confirmarCompra();
        when(compraRepository.obtenerPorId(idCompra)).thenReturn(Optional.of(compra));

        // Act
        realizarReembolso.ejecutar("El juego llegó dañado", idCompra);

        // Assert
        assertEquals(EstadoPago.REEMBOLSADO, compra.consultarEstadoCompra());
        verify(compraRepository).guardar(compra);
    }

    @Test
    void reembolsoNoDeberiaGuardarSiLaCompraNoExiste() {
        // Arrange
        when(compraRepository.obtenerPorId(any())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(NoSuchElementException.class, () ->
                realizarReembolso.ejecutar("motivo", UUID.randomUUID()));

        verify(compraRepository, never()).guardar(any());
    }

    @Test
    void reembolsoNoDeberiaGuardarSiLaCompraSigueEnPendiente() {
        // Arrange: una compra pendiente rompe la regla "solo se reembolsa lo aprobado"
        Compra pendiente = Compra.realizarCompra(UUID.randomUUID(), List.of(UUID.randomUUID()),
                UUID.randomUUID(), LocalDateTime.now());
        when(compraRepository.obtenerPorId(any())).thenReturn(Optional.of(pendiente));

        // Act + Assert
        assertThrows(ReglaDominioException.class, () ->
                realizarReembolso.ejecutar("motivo", UUID.randomUUID()));

        verify(compraRepository, never()).guardar(any());
    }

    // ---------- CrearJuegoMesaUseCase (vendedor) ----------

    @Test
    void crearJuegoNoDeberiaGuardarSiElStockEsNegativo() {
        // Act + Assert
        assertThrows(ReglaDominioException.class, () ->
                crearJuegoMesa.ejecutar(UUID.randomUUID(), UUID.randomUUID(), "Catan",
                        "Juego de estrategia", new Precio(120000), -1,
                        new RangoJugadores(4, 2), ComplejidadJuego.INTERMEDIA,
                        "Comercio", "Colonización", 10, "http://img/catan.png",
                        EstadoJuego.DISPONIBLE));

        verify(juegoMesaRepository, never()).guardar(any());
    }
}