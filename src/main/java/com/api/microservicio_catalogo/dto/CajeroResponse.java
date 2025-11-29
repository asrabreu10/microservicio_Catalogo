package com.api.microservicio_catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder

public class CajeroResponse {
    @JsonProperty("Id cajero:")
    Integer idCajero;
    
    @JsonProperty("Id usuario:")
    Long idUsuario;
    
    String nombre;
    String turno;
}
