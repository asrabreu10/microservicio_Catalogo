package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_venta")
    private Integer idVenta;

    // Relación a cajeros (_id_cajero)
    //@Column(name = "_id_cajero", nullable = false)
    //private Integer idCajero;

    // Relación a clientes (_id_cliente)
    //@Column(name = "_id_cliente")
    //private Integer idCliente;

    // Relación a metodo_pagos (_id_metodo_pago)
    //@Column(name = "_id_metodo_pago")
    //private Integer idMetodoPago;

    // Relación ManyToOne con Cajero (FK: _id_cajero)
    @ManyToOne
    @JoinColumn(name = "_id_cajero", referencedColumnName = "_id_cajero", nullable = false)
    private Cajero cajero;

    // Relación ManyToOne con Cliente (FK: _id_cliente)
    // ON DELETE SET NULL permite que sea nullable
    @ManyToOne
    @JoinColumn(name = "_id_cliente", referencedColumnName = "_id_cliente")
    private Cliente cliente;

    // Relación ManyToOne con MetodosPago (FK: _id_metodo_pago)
    // ON DELETE SET NULL permite que sea nullable
    @ManyToOne
    @JoinColumn(name = "_id_metodo_pago", referencedColumnName = "_id_metodo_pago")
    private MetodosPago metodoPago;
    @Column(name = "_fecha_venta", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaVenta;

    @Column(name = "_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
}
