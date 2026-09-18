package com.uniquindio.ecommerce.application;

import com.uniquindio.ecommerce.domain.entity.JuegoMesa;

public class RealizarVenta
{
    public boolean realizarVenta(JuegoMesa juegoMesa, int cantidad,float monto){
        float valor = juegoMesa.valorJuegoMesa().sacarPrecio() * cantidad;
        if (monto>=valor){
            return true;
        }
        return false;

    }
}

