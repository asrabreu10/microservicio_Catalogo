package com.api.microservicio_catalogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cajeros")
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_cajero")
    private Integer idCajero;

    // Relación OneToOne con Usuario (FK: _id_usuario)
    //@OneToOne
    //@Column(name = "_id_usuario", nullable = false)
    //private Integer idUsuario;

    @OneToOne
    @JoinColumn(name = "_id_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column(name = "_nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "_turno", nullable = false)
    private String turno;
}



   