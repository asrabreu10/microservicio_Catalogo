package com.api.microservicio_catalogo.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
//import lombok.Builder;
//import lombok.Value;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    @JsonProperty("IdCliente")
    Integer idCliente;
    
    String nombre;
    String apellido;
    String telefono;
    String email;
    
    @JsonProperty("Id direccion:")
    Integer idDireccion;
    
    @JsonProperty("Fecha registro:")
    Instant fechaRegistro;

}


