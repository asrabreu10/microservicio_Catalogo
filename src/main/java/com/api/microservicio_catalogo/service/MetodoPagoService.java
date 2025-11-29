package com.api.microservicio_catalogo.service;

import java.util.*;

import com.api.microservicio_catalogo.dto.MetodoPagoRequest;
import com.api.microservicio_catalogo.dto.MetodoPagoResponse;

public interface MetodoPagoService {

    List<MetodoPagoResponse> findAll();
    
    MetodoPagoResponse findById(Integer idMetodoPago);

    MetodoPagoResponse create(MetodoPagoRequest req);

    MetodoPagoResponse update(Integer idMetodoPago, MetodoPagoRequest req);

    void delete(Integer idMetodoPago);

    MetodoPagoResponse findByTipo(String tipoPago);
    public List<MetodoPagoResponse> getAll(int page, int pageSize);

}
