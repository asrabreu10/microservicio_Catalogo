package com.api.microservicio_catalogo.service;

import java.util.*;

import com.api.microservicio_catalogo.dto.ClienteRequest;
import com.api.microservicio_catalogo.dto.ClienteResponse;

public interface ClienteService {
    List<ClienteResponse> findAll();

    ClienteResponse findById(Integer idCliente);

    ClienteResponse create(ClienteRequest req);

    ClienteResponse update(Integer idCliente, ClienteRequest req);

    void delete(Integer idCliente);

    public List<ClienteResponse> findClienteByCiudad(String ciudad);
    
    public List<ClienteResponse> findClienteByNombre(String nombre, String apellido);
    
    //public List<ClienteResponse> getAll(int page, int pageSize);
    public List<ClienteResponse> findAll(int page, int pageSize);
}
