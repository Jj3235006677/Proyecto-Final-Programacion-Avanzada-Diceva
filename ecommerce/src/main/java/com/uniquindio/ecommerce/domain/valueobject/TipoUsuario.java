package com.uniquindio.ecommerce.domain.valueobject;

public enum TipoUsuario {
    CLIENTE,
    PROVEDOR,
    ADMINISTRADOR;

    public boolean puedePasar(TipoUsuario usuarioSiguiente){
        return switch (this){
            case CLIENTE -> usuarioSiguiente==PROVEDOR;
            case PROVEDOR ->usuarioSiguiente==CLIENTE;
            case ADMINISTRADOR -> false;
        };
    }


}
