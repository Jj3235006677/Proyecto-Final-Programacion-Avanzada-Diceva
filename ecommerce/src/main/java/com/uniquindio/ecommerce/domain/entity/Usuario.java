package com.uniquindio.ecommerce.domain.entity;

import com.uniquindio.ecommerce.domain.exeption.ReglaDominioException;
import com.uniquindio.ecommerce.domain.valueobject.TipoUsuario;

import java.util.UUID;

public class Usuario {
    final UUID cedula;
    String nombre;
    String apellido;
    String correo;
    String contraseña;
    TipoUsuario tipoUsuario;


    public Usuario(UUID cedula, String nombre, String apellido, String correo, String contraseña, TipoUsuario tipoUsuario) {
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contraseña.isEmpty() ){
            throw  new ReglaDominioException("No se puede dejar campos vacios");
        }
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }



}
