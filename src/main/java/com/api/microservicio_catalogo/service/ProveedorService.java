package com.api.microservicio_catalogo.service;

import java.util.*;


import com.api.microservicio_catalogo.dto.ProveedorRequest;
import com.api.microservicio_catalogo.dto.ProveedorResponse;

public interface ProveedorService {

    List<ProveedorResponse> findAll();

    ProveedorResponse findById(Integer idProveedor);

    ProveedorResponse create(ProveedorRequest req);

    ProveedorResponse update(Integer idProveedor, ProveedorRequest req);

    void delete(Integer idProveedor);

    ProveedorResponse findByNombreEmpresa(String nombreEmpresa);
    public List<ProveedorResponse> findAll(int page, int pageSize);

}
