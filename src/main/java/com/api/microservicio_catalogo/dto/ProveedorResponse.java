package com.api.microservicio_catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProveedorResponse {
    @JsonProperty("Id proveedor:")
    Integer idProveedor;
    
    @JsonProperty("Nombre empresa:")
    String nombreEmpresa;
    
    String contacto;
    String telefono;
    String email;
    
    @JsonProperty("Id direccion:")
    Integer idDireccion;
}
