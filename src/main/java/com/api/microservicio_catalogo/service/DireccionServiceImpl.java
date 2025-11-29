package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.DireccionRequest;
import com.api.microservicio_catalogo.dto.DireccionResponse;
import com.api.microservicio_catalogo.mapper.DireccionMapper;
import com.api.microservicio_catalogo.model.Direccion;
import com.api.microservicio_catalogo.repository.DireccionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DireccionServiceImpl implements DireccionService{
        private final DireccionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<DireccionResponse> findAll() {
        return repository.findAll().stream()
                .map(DireccionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DireccionResponse findById(Integer idDireccion) {
        Direccion direccion = repository.findById(idDireccion)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada: " + idDireccion));
        return DireccionMapper.toResponse(direccion);
    }

    @Override
    @Transactional
    public DireccionResponse create(DireccionRequest req) {
        Direccion saved = repository.save(DireccionMapper.toEntity(req));
        return DireccionMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public DireccionResponse update(Integer idDireccion, DireccionRequest req) {
        Direccion existing = repository.findById(idDireccion)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada: " + idDireccion));
        
        DireccionMapper.copyToEntity(req, existing);
        
        Direccion saved = repository.save(existing);
        return DireccionMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idDireccion) {
        if (!repository.existsById(idDireccion)) {
            throw new EntityNotFoundException("Dirección no encontrada: " + idDireccion);
        }
        // La eliminación se maneja por la base de datos (ON DELETE SET NULL en Cliente/Proveedor)
        repository.deleteById(idDireccion);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<DireccionResponse> findByCiudadAndEstado(String ciudad, String estado) {
        return repository.findDireccionesByCiudadAndEstado(ciudad, estado).stream()
                .map(DireccionMapper::toResponse)
                .toList();
    }

        @Override
    public List<DireccionResponse> getAll(int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<Direccion> direc = repository.findAll(pageReq);
		return direc.getContent().stream()
                .map(DireccionMapper::toResponse)
                .toList();
	}
}
