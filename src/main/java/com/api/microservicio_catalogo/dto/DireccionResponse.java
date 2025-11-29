package com.api.microservicio_catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionResponse {
    @JsonProperty("id Direccion")
    Integer idDireccion;
    
    String calle;
    String numero;
    String colonia;
    String ciudad;
    String estado;
    
    @JsonProperty("Codigo Postal")
    String codigoPostal;
}