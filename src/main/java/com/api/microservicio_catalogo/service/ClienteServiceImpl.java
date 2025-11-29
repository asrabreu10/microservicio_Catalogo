package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.ClienteRequest;
import com.api.microservicio_catalogo.dto.ClienteResponse;
import com.api.microservicio_catalogo.mapper.ClienteMapper;
import com.api.microservicio_catalogo.model.Cliente;
import com.api.microservicio_catalogo.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> findAll() {
        return repository.findAll().stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse findById(Integer idCliente) {
        Cliente cliente = repository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + idCliente));
        return ClienteMapper.toResponse(cliente);
    }

    @Override
    @Transactional
    public ClienteResponse create(ClienteRequest req) {
        // Asumiendo que la validación de idDireccion (FK) se realiza por la BD o si se
        // inyecta DireccionRepository.
        Cliente saved = repository.save(ClienteMapper.toEntity(req));
        return ClienteMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ClienteResponse update(Integer idCliente, ClienteRequest req) {
        Cliente existing = repository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + idCliente));

        ClienteMapper.copyToEntity(req, existing);

        Cliente saved = repository.save(existing);
        return ClienteMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idCliente) {
        if (!repository.existsById(idCliente)) {
            throw new EntityNotFoundException("Cliente no encontrado: " + idCliente);
        }
        repository.deleteById(idCliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> findClienteByNombre(String nombre, String apellido) {
        // Implementación asume que el Repository maneja la búsqueda combinada con LIKE
        return repository.findClientsByNombre(nombre, apellido).stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

 @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> findClienteByCiudad(String ciudad) {
        return repository.findClientsByCiudad(ciudad).stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }
    
 
    @Override
    public List<ClienteResponse> findAll(int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<Cliente> cliente = repository.findAll(pageReq);
		return cliente.getContent().stream()
                .map(ClienteMapper::toResponse)
                .toList();
	}
}