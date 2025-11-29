package com.api.microservicio_catalogo.service;

import java.util.*;
import com.api.microservicio_catalogo.dto.CajeroRequest;
import com.api.microservicio_catalogo.dto.CajeroResponse;


public interface CajeroService {

    List<CajeroResponse> findAll();
    
    CajeroResponse findById(Integer idCajero);

    CajeroResponse create(CajeroRequest req);

    CajeroResponse update(Integer idCajero, CajeroRequest req);

    void delete(Integer idCajero);
 
    public List<CajeroResponse> findCajeroByNombre(String nombre);
    public List<CajeroResponse> findCajerosByTurno(String turno);
    
    public List<CajeroResponse> getAll(int page, int pageSize);

}
