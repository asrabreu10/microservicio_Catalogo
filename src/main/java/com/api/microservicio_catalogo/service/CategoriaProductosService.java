package com.api.microservicio_catalogo.service;

import java.util.*;

import com.api.microservicio_catalogo.dto.CategoriaProductoRequest;
import com.api.microservicio_catalogo.dto.CategoriaProductoResponse;

public interface CategoriaProductosService {

    List<CategoriaProductoResponse> findAll();
   
    CategoriaProductoResponse findById(Integer idCategoria);

    CategoriaProductoResponse create(CategoriaProductoRequest req);

    CategoriaProductoResponse update(Integer idCategoria, CategoriaProductoRequest req);

    void delete(Integer idCategoria);

    CategoriaProductoResponse findCategoryByName(String nombreCategoria);

    public List<CategoriaProductoResponse> getAll(int page, int pageSize);
}
