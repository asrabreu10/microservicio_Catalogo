package com.api.microservicio_catalogo.service;

import java.util.List;

import com.api.microservicio_catalogo.dto.InventarioRequest;
import com.api.microservicio_catalogo.dto.InventarioResponse;

public interface InventarioService {

        
    InventarioResponse findByProductId(Integer idProducto);
    InventarioResponse createOrUpdate(InventarioRequest req);
    public List<InventarioResponse> getAll(int page, int pageSize);

    }
