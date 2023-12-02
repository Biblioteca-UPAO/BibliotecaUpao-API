package com.upao.biblioteca.controller;

import com.upao.biblioteca.domain.entity.Usuario;
import com.upao.biblioteca.domain.service.UsuarioService;
import com.upao.biblioteca.infra.security.LoginRequest;
import com.upao.biblioteca.infra.security.TokenResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<TokenResponse> addUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.addUsuario(usuario));
    }
}
