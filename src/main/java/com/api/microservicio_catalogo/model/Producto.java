package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_producto")
    private Integer idProducto;

    @Column(name = "_nombre_producto", columnDefinition = "TEXT")
    private String nombreProducto;

    @Column(name = "_descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "_precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "_stock", columnDefinition = "INT DEFAULT 0")
    private Integer stock;

    // Relación a categorias_productos (_id_categoria)
    //@Column(name = "_id_categoria")
    //private Integer idCategoria;

    // Relación a proveedores (_id_proveedor)
    //@Column(name = "_id_proveedor")
    //private Integer idProveedor;

    // Relación ManyToOne con CategoriaProducto (FK: _id_categoria)
    // ON DELETE SET NULL permite que la categoría sea opcional
    @ManyToOne
    @JoinColumn(name = "_id_categoria", referencedColumnName = "_id_categoria")
    private CategoriaProducto categoria;

    // Relación ManyToOne con Proveedor (FK: _id_proveedor)
    // ON DELETE SET NULL permite que el proveedor sea opcional
    @ManyToOne
    @JoinColumn(name = "_id_proveedor", referencedColumnName = "_id_proveedor")
    private Proveedor proveedor;
}
