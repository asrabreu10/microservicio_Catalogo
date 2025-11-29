package com.api.microservicio_catalogo.service;

import java.util.List;
import com.api.microservicio_catalogo.dto.ComentarioRequest;
import com.api.microservicio_catalogo.dto.ComentarioResponse;

public interface ComentarioService {

    List<ComentarioResponse> findAll();

    ComentarioResponse findById(Long idComentario);

    ComentarioResponse create(ComentarioRequest req);

    void delete(Long idComentario);

    public List<ComentarioResponse> findCommentsByAutor(String autor);

    public List<ComentarioResponse> findByKeyword(String keyword);

    public List<ComentarioResponse> getAll(int page, int pageSize);
}
