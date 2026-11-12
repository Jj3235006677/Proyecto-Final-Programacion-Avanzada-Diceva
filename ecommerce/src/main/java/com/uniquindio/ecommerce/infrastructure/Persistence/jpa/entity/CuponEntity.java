package com.uniquindio.ecommerce.infrastructure.Persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "cupones")
@Getter
@Setter
@NoArgsConstructor
public class CuponEntity {

    @Id
    @Column(name = "codigo", nullable = false, length = 30)
    private String codigo;

    @Column(name = "porcentaje", nullable = false)
    private int porcentaje;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;
}