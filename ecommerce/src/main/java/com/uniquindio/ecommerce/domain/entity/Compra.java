package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.EstadoPago;
import com.uniquindio.ecommerce.domain.valueobject.Precio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Compra {

    private final UUID id;
    private final List<UUID> idListaJuegosMesa;
    private final UUID cedulaUsuario;
    private  Precio precioTotal=new Precio(0);
    private final LocalDateTime fechaCompra;
    private  EstadoPago estadoPago;



    private Compra(
            UUID id,
            List<UUID> idListaJuegosMesa,
            UUID cedulaUsuario,
            LocalDateTime fechaCompra) {

        if (id == null || cedulaUsuario == null
                || precioTotal == null || fechaCompra == null) {

            throw new ReglaDominioException(
                    "La compra debe tener toda la informacion obligatoria");
        }

        this.id = id;
        this.idListaJuegosMesa = idListaJuegosMesa;
        this.cedulaUsuario = cedulaUsuario;

        this.fechaCompra = fechaCompra;
        this.estadoPago = EstadoPago.PENDIENTE;
    }

    // Única puerta de entrada para crear una Compra.
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

    public void sumarContidadesDinero(int valor){
        precioTotal.sumar(valor);
    }

    public void confirmarCompra() {
        if (!estadoPago.puedeTransicionarA(EstadoPago.APROBADO)) {
            throw new ReglaDominioException(
                    "No se puede confirmar la compra porque su pago no está pendiente"
            );
        }

        this.estadoPago = EstadoPago.APROBADO;
    }
    public void agregarJuego(UUID idJuego, int cantidad,int valorSumar) {

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

        for (int i = 0; i < cantidad; i++) {
            idListaJuegosMesa.add(idJuego);
        }

        precioTotal = precioTotal.sumar(Math.multiplyExact(cantidad, valorSumar));
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



    public EstadoPago consultarEstadoCompra(){
        return estadoPago;
    }

    public UUID getId() {
        return id;
    }

    public List<UUID> getIdListaJuegosMesa() {
        return idListaJuegosMesa;
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
        if (this == o) return true;

        if (!(o instanceof Compra)) return false;

        Compra compra = (Compra) o;

        return id.equals(compra.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }}