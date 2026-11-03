
package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.DetalleCompra;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Compra {

    private final UUID id;
    private final List<DetalleCompra> detalles;
    private final String cedulaUsuario;
    private Precio precioTotal;
    private final LocalDateTime fechaCompra;
    private EstadoPago estadoPago;

    private Compra(
            UUID id,
            String cedulaUsuario,
            LocalDateTime fechaCompra) {

        this.id = id;
        this.detalles = new ArrayList<>();
        this.cedulaUsuario = cedulaUsuario;
        this.fechaCompra = fechaCompra;
        this.precioTotal = new Precio(0);
        this.estadoPago = EstadoPago.PENDIENTE;
    }

    private static void validarDatosCompra(
            UUID id,
            String cedulaUsuario,
            LocalDateTime fechaCompra) {

        if (id == null) {
            throw new ReglaDominioException(
                    "El identificador de la compra es obligatorio"
            );
        }

        if (cedulaUsuario == null || cedulaUsuario.isBlank()) {
            throw new ReglaDominioException(
                    "La cédula del usuario es obligatoria"
            );
        }

        if (fechaCompra == null) {
            throw new ReglaDominioException(
                    "La fecha de compra es obligatoria"
            );
        }
    }

    public static Compra realizarCompra(
            UUID id,
            String cedulaUsuario,
            LocalDateTime fechaCompra) {

        validarDatosCompra(id, cedulaUsuario, fechaCompra);

        return new Compra(
                id,
                cedulaUsuario,
                fechaCompra
        );
    }

    public static Compra reconstruir(
            UUID id,
            List<DetalleCompra> detalles,
            String cedulaUsuario,
            LocalDateTime fechaCompra,
            Precio precioTotal,
            EstadoPago estadoPago) {

        validarDatosReconstruccion(
                id,
                detalles,
                cedulaUsuario,
                fechaCompra,
                precioTotal,
                estadoPago
        );

        Compra compra = new Compra(
                id,
                cedulaUsuario,
                fechaCompra
        );

        compra.detalles.addAll(detalles);
        compra.precioTotal = precioTotal;
        compra.estadoPago = estadoPago;

        return compra;
    }

    public void sumarContidadesDinero(Precio valor) {

        if (valor == null) {
            throw new ReglaDominioException(
                    "El precio que se suma es obligatorio"
            );
        }

        precioTotal = precioTotal.sumar(valor);
    }

    public void confirmarCompra() {

        if (!estadoPago.puedeTransicionarA(EstadoPago.APROBADO)) {
            throw new ReglaDominioException(
                    "No se puede confirmar la compra porque su pago no está pendiente"
            );
        }

        this.estadoPago = EstadoPago.APROBADO;
    }

    public void agregarJuego(
            UUID idJuego,
            int cantidad,
            Precio precioUnitario) {

        if (estadoPago != EstadoPago.PENDIENTE) {
            throw new ReglaDominioException(
                    "Solo se pueden agregar juegos a una compra pendiente"
            );
        }

        DetalleCompra detalle = new DetalleCompra(
                idJuego,
                cantidad,
                precioUnitario
        );

        detalles.add(detalle);

        precioTotal = precioTotal.sumar(detalle.subtotal());
    }

    public void realizarReembolso(String motivo) {

        if (estadoPago != EstadoPago.APROBADO) {
            throw new ReglaDominioException(
                    "Solo se pueden reembolsar compras con pago aprobado"
            );
        }

        if (motivo == null || motivo.isBlank()) {
            throw new ReglaDominioException(
                    "Debe indicar el motivo del reembolso"
            );
        }

        if (!estadoPago.puedeTransicionarA(EstadoPago.REEMBOLSADO)) {
            throw new ReglaDominioException(
                    "No se puede realizar el reembolso"
            );
        }

        this.estadoPago = EstadoPago.REEMBOLSADO;
    }

    public void verificarQuePermiteComentar(String cedula, UUID idJuego) {
        if (cedula == null || !cedulaUsuario.equals(cedula)) {
            throw new ReglaDominioException("La compra no pertenece al usuario que intenta comentar");
        }
        if (estadoPago != EstadoPago.APROBADO) {
            throw new ReglaDominioException("Solo se puede comentar un juego de una compra con pago aprobado");
        }
        if (!contieneJuego(idJuego)) {
            throw new ReglaDominioException("El juego que intenta comentar no hace parte de esta compra");
        }
    }

    public boolean contieneJuego(UUID idJuego) {
        return idJuego != null && detalles.stream()
                .anyMatch(detalle -> detalle.idJuego().equals(idJuego));
    }

    public EstadoPago consultarEstadoCompra() {
        return estadoPago;
    }

    public UUID getId() {
        return id;
    }

    public List<DetalleCompra> getDetalles() {
        return List.copyOf(detalles);
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public Precio getPrecioTotal() {
        return precioTotal;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    private static void validarDatosReconstruccion(
            UUID id,
            List<DetalleCompra> detalles,
            String cedulaUsuario,
            LocalDateTime fechaCompra,
            Precio precioTotal,
            EstadoPago estadoPago) {

        validarDatosCompra(id, cedulaUsuario, fechaCompra);

        if (detalles == null) {
            throw new ReglaDominioException(
                    "La lista de detalles de compra es obligatoria"
            );
        }

        if (detalles.stream().anyMatch(detalle -> detalle == null)) {
            throw new ReglaDominioException(
                    "La lista no puede contener detalles nulos"
            );
        }

        if (precioTotal == null || estadoPago == null) {
            throw new ReglaDominioException(
                    "No se puede reconstruir una compra con datos incompletos"
            );
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Compra)) {
            return false;
        }

        Compra compra = (Compra) o;

        return id.equals(compra.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
