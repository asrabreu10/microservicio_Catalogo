package com.api.microservicio_catalogo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "login")

public class Login {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_login")
    private Integer idLogin;

    // Relación OneToOne con Usuario (FK: _id_usuario)
    //@OneToOne
    //@Column(name = "_id_usuario", nullable = false)
    //private Integer idUsuario; 

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
    
    @Column(name = "_username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "_password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "_ultimo_acceso")
    private LocalDateTime ultimoAcceso;
}
