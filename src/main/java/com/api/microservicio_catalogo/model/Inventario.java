package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inventarios")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_inventario")
    private Integer idInventario;

      // Relación ManyToOne con Producto (FK: _id_producto)
    //@ManyToOne
    //@Column(name = "_id_producto", nullable = false)
    //private Integer idProducto;

    @ManyToOne
    @JoinColumn(name = "_id_producto", referencedColumnName = "_id_producto", nullable = false)
    private Producto producto; // Asumiendo que existe una clase Producto

    @Column(name = "_cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "_fecha_actualizacion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant fechaActualizacion;
}



  
