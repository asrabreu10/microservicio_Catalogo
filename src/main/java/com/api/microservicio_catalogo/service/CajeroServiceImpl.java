package com.api.microservicio_catalogo.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.microservicio_catalogo.dto.CajeroRequest;
import com.api.microservicio_catalogo.dto.CajeroResponse;
import com.api.microservicio_catalogo.mapper.CajeroMapper;
import com.api.microservicio_catalogo.model.Cajero;
import com.api.microservicio_catalogo.repository.CajeroRepository;
import com.api.microservicio_catalogo.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CajeroServiceImpl implements CajeroService {

    private final CajeroRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CajeroResponse> findAll() {
        return repository.findAll().stream()
                .map(CajeroMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CajeroResponse findById(Integer idCajero) {
        Cajero cajero = repository.findById(idCajero)
                .orElseThrow(() -> new EntityNotFoundException("Cajero no encontrado: " + idCajero));
        return CajeroMapper.toResponse(cajero);
    }

    @Override
    @Transactional
    public CajeroResponse create(CajeroRequest req) {
        // 1. Validar que el usuario existe (FK)
        if (!usuarioRepository.existsById(req.getIdUsuario())) {
            throw new EntityNotFoundException("Usuario asociado no encontrado.");
        }
        if (repository.findCajeroByNombre(req.getNombre()).stream().findFirst().isPresent()) {
            throw new IllegalArgumentException("Ya existe un cajero con el nombre: " + req.getNombre());
        }

        Cajero saved = repository.save(CajeroMapper.toEntity(req));
        return CajeroMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CajeroResponse update(Integer idCajero, CajeroRequest req) {
        Cajero existing = repository.findById(idCajero)
                .orElseThrow(() -> new EntityNotFoundException("Cajero no encontrado: " + idCajero));

        if (!usuarioRepository.existsById(req.getIdUsuario())) {
            throw new EntityNotFoundException("Usuario asociado no encontrado.");
        }

        repository.findCajeroByNombre(req.getNombre()).stream()
                .filter(c -> !c.getIdCajero().equals(idCajero))
                .findFirst()
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Ya existe otro cajero con el nombre: " + req.getNombre());
                });

        CajeroMapper.copyToEntity(req, existing);
        Cajero saved = repository.save(existing);
        return CajeroMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idCajero) {
        if (!repository.existsById(idCajero)) {
            throw new EntityNotFoundException("Cajero no encontrado: " + idCajero);
        }
        repository.deleteById(idCajero);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CajeroResponse> findCajerosByTurno(String turno) {
        return repository.findCajerosByTurno(turno).stream()
                .map(CajeroMapper::toResponse)
                .toList();
    }

    @Override  
    @Transactional(readOnly = true)
    public List<CajeroResponse> findCajeroByNombre(String nombre) {
        return repository.findCajeroByNombre(nombre).stream()
                .map(CajeroMapper::toResponse)
                .toList();
    }

    @Override
    public List<CajeroResponse> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Cajero> cajero = repository.findAll(pageReq);
        return cajero.getContent().stream()
                .map(CajeroMapper::toResponse)
                .toList();
    }
}

