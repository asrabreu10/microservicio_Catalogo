package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.DetalleVentaResponse;
import com.api.microservicio_catalogo.mapper.DetalleVentaMapper;
import com.api.microservicio_catalogo.model.DetalleVenta;
import com.api.microservicio_catalogo.repository.DetalleVentaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaService {
        private final DetalleVentaRepository repository;

        @Override
        @Transactional(readOnly = true)
        public DetalleVentaResponse findById(Integer idDetalle) {
                DetalleVenta detalle = repository.findById(idDetalle)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Detalle de Venta no encontrado: " + idDetalle));
                return DetalleVentaMapper.toResponse(detalle);
        }

        @Override
        @Transactional(readOnly = true)
        public List<DetalleVentaResponse> findByVentaId(Integer idVenta) {
                return repository.findDetailsBySaleId(idVenta).stream()
                                .map(DetalleVentaMapper::toResponse)
                                .toList();
        }

        @Override
        public List<DetalleVentaResponse> getAll(int page, int pageSize) {
                PageRequest pageReq = PageRequest.of(page, pageSize);
                Page<DetalleVenta> dtventa = repository.findAll(pageReq);
                return dtventa.getContent().stream()
                                .map(DetalleVentaMapper::toResponse)
                                .toList();
        }
}
