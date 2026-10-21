package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Compra {

    private final UUID id;
    private final List<UUID> idListaJuegosMesa;
    private final UUID cedulaUsuario;
    private Precio precioTotal;
    private final LocalDateTime fechaCompra;
    private EstadoPago estadoPago;

    private Compra(
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedulaUsuario,
            LocalDateTime fechaCompra) {

        // Validar el identificador de la compra
        if (id == null) {
            throw new ReglaDominioException(
                    "El identificador de la compra es obligatorio"
            );
        }

        // Validar que la lista exista y contenga al menos un juego
        if (idListaJuegosMesa == null || idListaJuegosMesa.isEmpty()) {
            throw new ReglaDominioException(
                    "La compra debe tener al menos un juego"
            );
        }

        // Validar que ningún identificador de juego sea nulo
        for (UUID idJuego : idListaJuegosMesa) {
            if (idJuego == null) {
                throw new ReglaDominioException(
                        "La lista no puede contener identificadores de juegos nulos"
                );
            }
        }

        // Validar el usuario
        if (cedulaUsuario == null) {
            throw new ReglaDominioException(
                    "El identificador del usuario es obligatorio"
            );
        }

        // Validar la fecha
        if (fechaCompra == null) {
            throw new ReglaDominioException(
                    "La fecha de compra es obligatoria"
            );
        }

        this.id = id;
        this.idListaJuegosMesa = new ArrayList<>(idListaJuegosMesa);
        this.cedulaUsuario = cedulaUsuario;
        this.fechaCompra = fechaCompra;
        this.precioTotal = new Precio(0);
        this.estadoPago = EstadoPago.PENDIENTE;
    }

    // NUEVO: constructor para reconstruir una compra existente
    private Compra(
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedulaUsuario,
            LocalDateTime fechaCompra,
            Precio precioTotal,
            EstadoPago estadoPago) {

        this.id = id;
        this.idListaJuegosMesa = new ArrayList<>(idListaJuegosMesa);
        this.cedulaUsuario = cedulaUsuario;
        this.fechaCompra = fechaCompra;
        this.precioTotal = precioTotal;
        this.estadoPago = estadoPago;
    }

    public static Compra realizarCompra(
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedulaUsuario,
            LocalDateTime fechaCompra) {

        return new Compra(
                id,
                idListaJuegosMesa,
                cedulaUsuario,
                fechaCompra
        );
    }

    // NUEVO: reconstruir una compra recuperada de la base de datos
    public static Compra reconstruir(
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedulaUsuario,
            LocalDateTime fechaCompra,
            Precio precioTotal,
            EstadoPago estadoPago) {

        if (id == null
                || idListaJuegosMesa == null
                || idListaJuegosMesa.isEmpty()
                || cedulaUsuario == null
                || fechaCompra == null
                || precioTotal == null
                || estadoPago == null) {

            throw new ReglaDominioException(
                    "No se puede reconstruir una compra con datos incompletos"
            );
        }

        for (UUID idJuego : idListaJuegosMesa) {
            if (idJuego == null) {
                throw new ReglaDominioException(
                        "La lista no puede contener identificadores de juegos nulos"
                );
            }
        }

        return new Compra(
                id,
                idListaJuegosMesa,
                cedulaUsuario,
                fechaCompra,
                precioTotal,
                estadoPago
        );
    }

    public void sumarContidadesDinero(int valor) {

        if (valor < 0) {
            throw new ReglaDominioException(
                    "El valor que se suma no puede ser negativo"
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

    public void agregarJuego(UUID idJuego, int cantidad, int valorSumar) {

        if (estadoPago != EstadoPago.PENDIENTE) {
            throw new ReglaDominioException(
                    "Solo se pueden agregar juegos a una compra pendiente"
            );
        }

        if (idJuego == null) {
            throw new ReglaDominioException(
                    "El identificador del juego es obligatorio"
            );
        }

        if (cantidad <= 0) {
            throw new ReglaDominioException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        if (valorSumar < 0) {
            throw new ReglaDominioException(
                    "El valor del juego no puede ser negativo"
            );
        }

        int valorTotal;

        try {
            valorTotal = Math.multiplyExact(cantidad, valorSumar);
        } catch (ArithmeticException e) {
            throw new ReglaDominioException(
                    "El valor total de los juegos excede el límite permitido"
            );
        }

        precioTotal = precioTotal.sumar(valorTotal);

        for (int i = 0; i < cantidad; i++) {
            idListaJuegosMesa.add(idJuego);
        }
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

    public EstadoPago consultarEstadoCompra() {
        return estadoPago;
    }

    public UUID getId() {
        return id;
    }

    public List<UUID> getIdListaJuegosMesa() {
        return List.copyOf(idListaJuegosMesa);
    }

    public UUID getCedulaUsuario() {
        return cedulaUsuario;
    }

    public Precio getPrecioTotal() {
        return precioTotal;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
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