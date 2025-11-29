package com.api.microservicio_catalogo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.microservicio_catalogo.dto.UserLoginRequest;
import com.api.microservicio_catalogo.dto.UserLoginResponse;
import com.api.microservicio_catalogo.dto.UsuarioRequest;
import com.api.microservicio_catalogo.dto.UsuarioResponse;
import com.api.microservicio_catalogo.mapper.UserLoginMapper;
import com.api.microservicio_catalogo.model.Usuario;
import com.api.microservicio_catalogo.service.JwtService;
import com.api.microservicio_catalogo.service.UsuarioService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor

public class UsuarioController {
    
    private final UsuarioService service;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

   @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UsuarioResponse created = service.create(user);
        return created;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody UserLoginRequest loginRequest) {             
        Usuario authenticatedUser = service.authenticate(loginRequest);        
        UserLoginResponse userLoginResponse = UserLoginMapper.toResponse(authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        userLoginResponse.setToken(jwtToken);
        userLoginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(userLoginResponse);
    }

    /*@PostMapping("/login")
    public ResponseEntity<UserResponse> authenticate(@RequestBody UserRequest request) {
        User authenticatedUser = service.authenticate(request);
        System.out.println(authenticatedUser.getEmail());
        String jwtToken = jwtService.generateToken(authenticatedUser);
        UserResponse userResponse = UserResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(userResponse);
    }*/
}
