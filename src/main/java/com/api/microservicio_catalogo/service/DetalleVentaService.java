package com.api.microservicio_catalogo.service;

import java.util.*;

import com.api.microservicio_catalogo.dto.DetalleVentaResponse;

public interface DetalleVentaService {

    DetalleVentaResponse findById(Integer idDetalle);

    List<DetalleVentaResponse> findByVentaId(Integer idVenta);

    public List<DetalleVentaResponse> getAll(int page, int pageSize);

}
