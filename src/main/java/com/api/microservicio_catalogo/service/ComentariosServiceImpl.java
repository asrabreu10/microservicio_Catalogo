package com.api.microservicio_catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio_catalogo.dto.ComentarioRequest;
import com.api.microservicio_catalogo.dto.ComentarioResponse;
import com.api.microservicio_catalogo.mapper.ComentarioMapper;
import com.api.microservicio_catalogo.model.Comentario;
import com.api.microservicio_catalogo.repository.ComentarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentariosServiceImpl implements ComentarioService {

    private final ComentarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioResponse> findAll() {
        return repository.findAll().stream()
                .map(ComentarioMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ComentarioResponse findById(Long idComentario) {
        Comentario comentario = repository.findById(idComentario)
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado: " + idComentario));
        return ComentarioMapper.toResponse(comentario);
    }

    @Override
    @Transactional
    public ComentarioResponse create(ComentarioRequest req) {
        Comentario saved = repository.save(ComentarioMapper.toEntity(req));
        return ComentarioMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long idComentario) {
        if (!repository.existsById(idComentario)) {
            throw new EntityNotFoundException("Comentario no encontrado: " + idComentario);
        }
        repository.deleteById(idComentario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioResponse> findByKeyword(String keyword) {
        return repository.findCommentsByKeyword(keyword).stream()
                .map(ComentarioMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioResponse> findCommentsByAutor(String autor) {
        return repository.findCommentsByAuthorLike(autor).stream()
                .map(ComentarioMapper::toResponse)
                .toList();
    }

    @Override
    public List<ComentarioResponse> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Comentario> com = repository.findAll(pageReq);
        return com.getContent().stream()
                .map(ComentarioMapper::toResponse)
                .toList();
    }
}
