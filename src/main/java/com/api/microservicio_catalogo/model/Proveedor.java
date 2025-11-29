package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "proveedores")
public class Proveedor {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_proveedor")
    private Integer idProveedor;

    @Column(name = "_nombre_empresa", nullable = false, length = 100)
    private String nombreEmpresa;

    @Column(name = "_contacto", length = 100)
    private String contacto;

    @Column(name = "_telefono", length = 20)
    private String telefono;

    @Column(name = "_email", length = 100)
    private String email;

    // Relación a direcciones (_id_direccion)
    //@Column(name = "_id_direccion")
    //private Integer idDireccion;

    // Relación ManyToOne con Direccion (FK: _id_direccion)
    // ON DELETE SET NULL permite que la dirección sea opcional
    @ManyToOne
    @JoinColumn(name = "_id_direccion", referencedColumnName = "_id_direccion")
    private Direccion direccion;
}
