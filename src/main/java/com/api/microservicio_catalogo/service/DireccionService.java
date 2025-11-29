package com.api.microservicio_catalogo.service;

import java.util.*;

import com.api.microservicio_catalogo.dto.DireccionRequest;
import com.api.microservicio_catalogo.dto.DireccionResponse;

public interface DireccionService {

    List<DireccionResponse> findAll();
    
    DireccionResponse findById(Integer idDireccion);

    DireccionResponse create(DireccionRequest req);

    DireccionResponse update(Integer idDireccion, DireccionRequest req);

    void delete(Integer idDireccion);

    public List<DireccionResponse> findByCiudadAndEstado(String ciudad, String estado);
    public List<DireccionResponse> getAll(int page, int pageSize);

}
