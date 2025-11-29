package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categorias_productos")
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_categoria")
    private Integer idCategoria;

    @Column(name = "_nombre_categoria", nullable = false, length = 100)
    private String nombreCategoria;

    @Column(name = "_descripcion",  nullable = false, length = 100)
    private String descripcion;
}
