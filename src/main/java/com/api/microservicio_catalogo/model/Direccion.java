package com.api.microservicio_catalogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_direccion")
    private Integer idDireccion;

    @Column(name = "_calle", length = 100)
    private String calle;

    @Column(name = "_numero", length = 10)
    private String numero;

    @Column(name = "_colonia", length = 100)
    private String colonia;

    @Column(name = "_ciudad", length = 100)
    private String ciudad;

    @Column(name = "_estado", length = 100)
    private String estado;

    @Column(name = "_codigo_postal", length = 10)
    private String codigoPostal;
}
