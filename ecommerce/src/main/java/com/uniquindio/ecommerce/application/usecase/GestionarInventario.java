package com.uniquindio.ecommerce.application.usecase;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;

public class GestionarInventario {

    public boolean hayStockDisponible(JuegoMesa juegoMesa, int cantidad) {

        return juegoMesa.consultarStock()>=cantidad;
    }
}