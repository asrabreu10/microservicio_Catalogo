package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.api.microservicio_catalogo.mapper.MetodoPagoMapper;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.MetodoPagoRequest;
import com.api.microservicio_catalogo.dto.MetodoPagoResponse;
import com.api.microservicio_catalogo.model.MetodosPago;
import com.api.microservicio_catalogo.repository.MetodoPagoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<MetodoPagoResponse> findAll() {
        return repository.findAll().stream()
                .map(MetodoPagoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MetodoPagoResponse findById(Integer idMetodoPago) {
        MetodosPago metodo = repository.findById(idMetodoPago)
                .orElseThrow(() -> new EntityNotFoundException("Método de pago no encontrado: " + idMetodoPago));
        return MetodoPagoMapper.toResponse(metodo);
    }

    @Override
    @Transactional
    public MetodoPagoResponse create(MetodoPagoRequest req) {
        if (repository.findPaymentMethodByType(req.getTipoPago()).isPresent()) {
            throw new IllegalArgumentException("El método de pago ya existe: " + req.getTipoPago());
        }
        MetodosPago saved = repository.save(MetodoPagoMapper.toEntity(req));
        return MetodoPagoMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public MetodoPagoResponse update(Integer idMetodoPago, MetodoPagoRequest req) {
        MetodosPago existing = repository.findById(idMetodoPago)
                .orElseThrow(() -> new EntityNotFoundException("Método de pago no encontrado: " + idMetodoPago));

        MetodoPagoMapper.copyToEntity(req, existing);

        MetodosPago saved = repository.save(existing);
        return MetodoPagoMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idMetodoPago) {
        if (!repository.existsById(idMetodoPago)) {
            throw new EntityNotFoundException("Método de pago no encontrado: " + idMetodoPago);
        }
        // La eliminación se maneja por la base de datos (ON DELETE SET NULL en Venta)
        repository.deleteById(idMetodoPago);
    }

    @Override
    @Transactional(readOnly = true)
    public MetodoPagoResponse findByTipo(String tipoPago) {
        MetodosPago metodo = repository.findPaymentMethodByType(tipoPago)
                .orElseThrow(() -> new EntityNotFoundException("Método de pago no encontrado: " + tipoPago));
        return MetodoPagoMapper.toResponse(metodo);
    }

    @Override
    public List<MetodoPagoResponse> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<MetodosPago> metPago = repository.findAll(pageReq);
        return metPago.getContent().stream()
                .map(MetodoPagoMapper::toResponse)
                .toList();
    }

}
