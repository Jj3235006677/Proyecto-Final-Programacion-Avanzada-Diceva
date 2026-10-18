package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable//dice que hace parte de una tabla
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrecioEmbeddable {

    @Column(name="precio_monto",nullable = false)
    private int monto;

}
