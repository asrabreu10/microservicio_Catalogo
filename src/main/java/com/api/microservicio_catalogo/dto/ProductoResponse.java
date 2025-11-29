package com.api.microservicio_catalogo.dto;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductoResponse {
    //@JsonProperty("Id producto:")
    Integer idProducto;
    
    //@JsonProperty("Nombre producto:")
    String nombreProducto;
    
    String descripcion;
    BigDecimal precio;
    Integer stock;
    
    //@JsonProperty("Id categoria:")
    Integer idCategoria;
    
   // @JsonProperty("Id proveedor:")
    Integer idProveedor;
}
