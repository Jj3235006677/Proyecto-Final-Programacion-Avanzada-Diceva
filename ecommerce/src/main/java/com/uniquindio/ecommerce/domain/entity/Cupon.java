package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exception.ReglaDominioException;

import java.time.LocalDate;

public class Cupon {

    private final String codigo;
    private final int porcentaje;
    private final LocalDate fechaVencimiento;

    private Cupon(String codigo, int porcentaje, LocalDate fechaVencimiento) {
        this.codigo = codigo;
        this.porcentaje = porcentaje;
        this.fechaVencimiento = fechaVencimiento;
    }

    public static Cupon reconstruir(String codigo, int porcentaje, LocalDate fechaVencimiento) {

        if (codigo == null || codigo.isBlank()) {
            throw new ReglaDominioException("El código del cupón es obligatorio");
        }

        if (porcentaje < 1 || porcentaje > 90) {
            throw new ReglaDominioException("El descuento del cupón debe estar entre 1% y 90%");
        }

        if (fechaVencimiento == null) {
            throw new ReglaDominioException("La fecha de vencimiento del cupón es obligatoria");
        }

        return new Cupon(codigo.toUpperCase(), porcentaje, fechaVencimiento);
    }

    public void verificarVigente(LocalDate hoy) {
        if (hoy.isAfter(fechaVencimiento)) {
            throw new ReglaDominioException("El cupón " + codigo + " ya venció");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cupon otro)) return false;
        return codigo.equals(otro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}