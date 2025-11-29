package com.api.microservicio_catalogo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
//@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class LoginController {
/*
private final LoginService loginService;

    @PostMapping("/authenticate")
    @Operation(summary = "Autenticar un usuario")
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa")
    @ApiResponse(responseCode = "404", description = "Credenciales inválidas")
    public ResponseEntity<UsuarioResponse> autenticarUsuario(@RequestParam String username, @RequestParam String password) {
        UsuarioResponse usuarioAutenticado = loginService.authenticate(username, password);
        return ResponseEntity.ok(usuarioAutenticado);
    }
/*
    @PostMapping("/credenciales")
    @Operation(summary = "Crear credenciales de acceso para un usuario")
    public ResponseEntity<UserLoginResponse> crearCredenciales(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse credenciales = loginService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/login/usuario/" + credenciales.getIdUsuario()))
                .body(credenciales);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener credenciales de login por ID de usuario (solo admin)")
    @ApiResponse(responseCode = "404", description = "Credenciales no encontradas para el usuario")
    public UserLoginResponse obtenerCredencialesPorUsuario(@PathVariable Integer idUsuario) {
        return loginService.findByIdUsuario(idUsuario);
    }

    @PutMapping("/{idLogin}")
    @Operation(summary = "Actualizar (cambiar) credenciales de acceso por ID de Login")
    public UserLoginResponse actualizarCredenciales(@PathVariable Integer idLogin, @Valid @RequestBody UserLoginRequest request) {
        return loginService.update(idLogin, request);
    }

    @DeleteMapping("/{idLogin}")
    @Operation(summary = "Eliminar credenciales de acceso por ID de Login")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCredenciales(@PathVariable Integer idLogin) {
        loginService.delete(idLogin);
    }

*/
    
}
