package com.api.microservicio_catalogo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.api.microservicio_catalogo.dto.UserLoginRequest;
import com.api.microservicio_catalogo.dto.UsuarioRequest;
import com.api.microservicio_catalogo.dto.UsuarioResponse;
import com.api.microservicio_catalogo.mapper.UsuarioMapper;
import com.api.microservicio_catalogo.model.Role;
import com.api.microservicio_catalogo.model.Usuario;
import com.api.microservicio_catalogo.repository.RoleRepository;
import com.api.microservicio_catalogo.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UsuarioResponse create(UsuarioRequest request) {
        String role = request.getRole();
        Role userRole = roleRepository.findByAuthority(role).orElseThrow(() -> new NoSuchElementException("Authority not present"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        Usuario user = UsuarioMapper.toEntity(request);
        user.setAuthorities(authorities);
        Usuario saved = userRepository.save(user);
        return UsuarioMapper.toResponse(saved);
    }

    public Usuario authenticate(UsuarioRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public Usuario authenticate(UserLoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
