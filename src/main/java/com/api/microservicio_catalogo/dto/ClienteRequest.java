package com.api.microservicio_catalogo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 100)
    private String apellido;

    @Size(max = 20)
    private String telefono;

    @Size(max = 100)
    @Email
    private String email;

    private Integer idDireccion; // FK (Opcional, ya que ON DELETE es SET NULL)
    
}
