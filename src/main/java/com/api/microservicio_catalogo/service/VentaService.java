package com.api.microservicio_catalogo.service;

import java.util.*;
import java.time.LocalDateTime;

import com.api.microservicio_catalogo.dto.VentaRequest;
import com.api.microservicio_catalogo.dto.VentaResponse;

public interface VentaService {

    List<VentaResponse> findAll();

    VentaResponse findById(Integer idVenta);

    VentaResponse createSale(VentaRequest req);

    void delete(Integer idVenta);

    List<VentaResponse> findByCajero(Integer idCajero);

    List<VentaResponse> findSalesByDateRange(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<VentaResponse> getAll(int page, int pageSize);

}
