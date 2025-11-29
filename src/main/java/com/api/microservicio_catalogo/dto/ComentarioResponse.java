package com.api.microservicio_catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import java.time.Instant; 

@Value
@Builder
public class ComentarioResponse {
    
    @JsonProperty("Id comentario:")
    Integer idComentario; 
    String titulo;
    String comentario;
    String autor;
    
    Instant fecha;
}


