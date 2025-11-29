package com.api.microservicio_catalogo.service;

/*
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.puntodeventa.dto.UserLoginRequest;
import com.api.puntodeventa.dto.UserLoginResponse;
import com.api.puntodeventa.dto.UsuarioResponse;
import com.api.puntodeventa.mapper.LoginMapper;
import com.api.puntodeventa.mapper.UsuarioMapper;
import com.api.puntodeventa.model.Login;
import com.api.puntodeventa.model.Usuario;
import com.api.puntodeventa.repository.LoginRepository;
import com.api.puntodeventa.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
 */
public class LoginServiceImpl implements LoginService {
/*
    private final LoginRepository loginRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserLoginResponse create(UserLoginRequest req) {
        // 1. Validar que el usuario existe
        if (!usuarioRepository.existsById(req.getIdUsuario())) {
            throw new EntityNotFoundException("Usuario asociado no encontrado.");
        }
        // 2. Validar que no exista ya un login para ese usuario (1:1 conceptual)
        if (loginRepository.findLoginByIdUsuario(req.getIdUsuario()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya tiene credenciales de acceso.");
        }

        Login entity = LoginMapper.toEntity(req);

        Login saved = loginRepository.save(entity);
        return LoginMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public UserLoginResponse update(Integer idLogin, UserLoginRequest req) {
        Login existing = loginRepository.findById(idLogin)
                .orElseThrow(() -> new EntityNotFoundException("Credenciales de Login no encontradas: " + idLogin));

        LoginMapper.copyToEntity(req, existing);

        Login saved = loginRepository.save(existing);
        return LoginMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer idLogin) {
        if (!loginRepository.existsById(idLogin)) {
            throw new EntityNotFoundException("Credenciales de Login no encontradas: " + idLogin);
        }
        loginRepository.deleteById(idLogin);
    }


    @Override
    @Transactional(readOnly = true)
    public UserLoginResponse findByIdUsuario(Integer idUsuario) {
        Login login = loginRepository.findLoginByIdUsuario(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Login no encontrado para el usuario: " + idUsuario));
        return LoginMapper.toResponse(login);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse authenticate(String username, String password) {
        Login login = loginRepository.findLoginByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Credenciales inválidas."));

        Usuario usuario = usuarioRepository.findById(login.getUsuario().getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario asociado al login no encontrado."));

        return UsuarioMapper.toResponse(usuario);
    }*/

    

}
