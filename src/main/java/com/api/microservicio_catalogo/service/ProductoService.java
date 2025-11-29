package com.api.microservicio_catalogo.service;

import java.util.*;

import com.api.microservicio_catalogo.dto.ProductoRequest;
import com.api.microservicio_catalogo.dto.ProductoResponse;

public interface ProductoService {
    
    List<ProductoResponse> findAll();

    ProductoResponse findById(Integer idProducto);

    ProductoResponse create(ProductoRequest req);

    ProductoResponse update(Integer idProducto, ProductoRequest req);

    void delete(Integer idProducto);

    public List<ProductoResponse> findByCategoria(Integer idCategoria);

    public List<ProductoResponse> findProductsWithLowStock(Integer cantidadMinima);

    public List<ProductoResponse> findAll(int page, int pageSize);

}
