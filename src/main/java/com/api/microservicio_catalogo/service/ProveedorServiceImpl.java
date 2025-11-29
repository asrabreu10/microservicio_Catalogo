package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.ProveedorRequest;
import com.api.microservicio_catalogo.dto.ProveedorResponse;
import com.api.microservicio_catalogo.mapper.ProveedorMapper;
import com.api.microservicio_catalogo.model.Proveedor;
import com.api.microservicio_catalogo.repository.ProveedorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponse> findAll() {
        return repository.findAll().stream()
                .map(ProveedorMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorResponse findById(Integer idProveedor) {
        Proveedor proveedor = repository.findById(idProveedor)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado: " + idProveedor));
        return ProveedorMapper.toResponse(proveedor);
    }

    @Override
    @Transactional
    public ProveedorResponse create(ProveedorRequest req) {
        // Validación: El nombre de la empresa debe ser único
        if (repository.findProviderByCompanyName(req.getNombreEmpresa()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un proveedor con el nombre de empresa: " + req.getNombreEmpresa());
        }
        Proveedor saved = repository.save(ProveedorMapper.toEntity(req));
        return ProveedorMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ProveedorResponse update(Integer idProveedor, ProveedorRequest req) {
        Proveedor existing = repository.findById(idProveedor)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado: " + idProveedor));

        ProveedorMapper.copyToEntity(req, existing);

        Proveedor saved = repository.save(existing);
        return ProveedorMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idProveedor) {
        if (!repository.existsById(idProveedor)) {
            throw new EntityNotFoundException("Proveedor no encontrado: " + idProveedor);
        }
        // La eliminación se maneja por la base de datos (ON DELETE SET NULL en
        // Producto)
        repository.deleteById(idProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorResponse findByNombreEmpresa(String nombreEmpresa) {
        Proveedor proveedor = repository.findProviderByCompanyName(nombreEmpresa)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con nombre: " + nombreEmpresa));
        return ProveedorMapper.toResponse(proveedor);
    }

    @Override
    public List<ProveedorResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Proveedor> metPago = repository.findAll(pageReq);
        return metPago.getContent().stream()
                .map(ProveedorMapper::toResponse)
                .toList();
    }
}
