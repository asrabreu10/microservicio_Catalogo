package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "metodo_pagos")
public class MetodosPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_metodo_pago")
    private Integer idMetodoPago;

    /*
     * @Enumerated(EnumType.STRING)
     * 
     * @Column(name = "_tipo_pago", nullable = false, columnDefinition =
     * "ENUM('efectivo','tarjeta','transferencia','otros')")
     * private String tipoPago;
     */

    @Column(name = "_tipo_pago", nullable = false)
    private String tipoPago;

    @Column(name = "_descripcion", length = 100)
    private String descripcion;
}
