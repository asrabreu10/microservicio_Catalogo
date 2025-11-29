package com.api.microservicio_catalogo.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_cliente")
    private Integer idCliente;

    @Column(name = "_nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "_apellido", length = 100)
    private String apellido;

    @Column(name = "_telefono", length = 20)
    private String telefono;

    @Column(name = "_email", length = 100)
    private String email;

    // Relación a direcciones (_id_direccion)
   // @ManyToOne
    //@Column(name = "_id_direccion")
    //private Integer idDireccion;

    @ManyToOne
    @JoinColumn(name = "_id_direccion", referencedColumnName = "_id_direccion")
    private Direccion direccion;

    @Column(name = "_fecha_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant fechaRegistro;
}
