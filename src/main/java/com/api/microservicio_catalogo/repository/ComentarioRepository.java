package com.api.microservicio_catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.api.microservicio_catalogo.model.Comentario;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    // Buscar comentarios por el autor
    @Query("SELECT c FROM Comentario c WHERE LOWER(c.autor) LIKE LOWER(CONCAT('%', :autor, '%'))")
    List<Comentario> findCommentsByAuthorLike(@Param("autor") String autor);

    // Buscar comentarios por palabras clave en el cuerpo (consulta nativa por CLOB)
    @Query(value = "SELECT * FROM comentarios WHERE LOWER(_comentario) LIKE CONCAT('%', LOWER(:keyword), '%')", nativeQuery = true)
    List<Comentario> findCommentsByKeyword(@Param("keyword") String keyword);
}