package com.api.microservicio_catalogo.mapper;

import com.api.microservicio_catalogo.dto.ComentarioRequest;
import com.api.microservicio_catalogo.dto.ComentarioResponse;
import com.api.microservicio_catalogo.model.Comentario;

public final class ComentarioMapper {
    public static ComentarioResponse toResponse(Comentario comentario) {
        if (comentario == null)
            return null;
        return ComentarioResponse.builder()
                .idComentario(comentario.getIdComentario())
                .titulo(comentario.getTitulo())
                .comentario(comentario.getComentario())
                .autor(comentario.getAutor())
                .fecha(comentario.getFecha())
                .build();
    }

    public static Comentario toEntity(ComentarioRequest dto) {
        if (dto == null)
            return null;
        return Comentario.builder()
                .titulo(dto.getTitulo())
                .comentario(dto.getComentario())
                .autor(dto.getAutor())
                .build();
    }

    public static void copyToEntity(ComentarioRequest dto, Comentario entity) {
        if (dto == null || entity == null)
            return;
        entity.setComentario(dto.getComentario());
        // No se copia la fecha
    }
}
