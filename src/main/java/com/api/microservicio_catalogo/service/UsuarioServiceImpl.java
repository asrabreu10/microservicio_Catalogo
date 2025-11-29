package com.api.microservicio_catalogo.service;

//import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;





//@Service
//@RequiredArgsConstructor
public class UsuarioServiceImpl  {
/*
    private final UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        return repository.findAll().stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse findById(Integer idUsuario) {
        Usuario usuario = repository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + idUsuario));
        return UsuarioMapper.toResponse(usuario);
    }

   /* @Override
    @Transactional
    public UsuarioResponse create(UsuarioRequest req) {
        // Validación adicional si el email ya existe
        if (repository.findUserByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }
        Usuario saved = repository.save(UsuarioMapper.toEntity(req));
        return UsuarioMapper.toResponse(saved);
    }
 
    @Override
    @Transactional
    public UsuarioResponse update(Integer idUsuario, UsuarioRequest req) {
        Usuario existing = repository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + idUsuario));

        // El mapper copia los datos del Request a la entidad existente
        UsuarioMapper.copyToEntity(req, existing);

        Usuario saved = repository.save(existing);
        return UsuarioMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idUsuario) {
        if (!repository.existsById(idUsuario)) {
            throw new EntityNotFoundException("Usuario no encontrado: " + idUsuario);
        }
        // Las claves foráneas con ON DELETE CASCADE se encargarán de eliminar Login y
        // Cajero
        repository.deleteById(idUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> findByRol(String rol) {
        return repository.findUsersByRol(rol).stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse findByEmail(String email) {
        Usuario usuario = repository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con email: " + email));
        return UsuarioMapper.toResponse(usuario);
    }
 
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Usuario> user = repository.findAll(pageReq);
        return user.getContent().stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }
     */
}

