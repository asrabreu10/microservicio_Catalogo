package com.api.microservicio_catalogo.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class VentaResponse {
    @JsonProperty("Id venta:")
    Integer idVenta;
    
    @JsonProperty("Id cajero:")
    Integer idCajero;
    
    @JsonProperty("Id cliente:")
    Integer idCliente;
    
    @JsonProperty("Id metodo pago:")
    Integer idMetodoPago;
    
    @JsonProperty("Fecha venta:")
    LocalDateTime fechaVenta;
    
    BigDecimal total;
    
    // NOTA: Para retornar una venta completa, se incluiría aquí un List<DetalleVentaResponse>
 
}

   