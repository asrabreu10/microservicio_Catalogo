package com.api.microservicio_catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoriaProductoResponse {
    @JsonProperty("Id categoria:")
    Integer idCategoria;
    
    @JsonProperty("Nombre categoria:")
    String nombreCategoria;
    
    String descripcion;
}
