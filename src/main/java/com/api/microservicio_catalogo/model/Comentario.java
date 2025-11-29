package com.api.microservicio_catalogo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    // Usamos Long para mapear SERIAL (que suele ser BIGINT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id_comentario")
    private Integer idComentario;

    @Column(name = "_titulo", length = 100)
    private String titulo;

    @Column(name = "_comentario", length = 100)
    private String comentario;

    @Column(name = "_autor", length = 100)
    private String autor;

    @Column(name = "_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant fecha;
}
