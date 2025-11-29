package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_detalle")
    private Integer idDetalle;

   // @Column(name = "_id_venta", nullable = false)
   // private Integer idVenta;

   //@Column(name = "_id_producto", nullable = false)
   // private Integer idProducto;

   // Relación ManyToOne con Venta (FK: _id_venta)
    @ManyToOne
    @JoinColumn(name = "_id_venta", referencedColumnName = "_id_venta", nullable = false)
    private Venta venta;

    // Relación ManyToOne con Producto (FK: _id_producto)
    @ManyToOne
    @JoinColumn(name = "_id_producto", referencedColumnName = "_id_producto", nullable = false)
    private Producto producto; // Asumiendo que existe una clase Producto
    @Column(name = "_cantidad", nullable = false)
    private Integer cantidad;


    @Column(name = "_precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "_subtotal", insertable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    
}
