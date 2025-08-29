package com.fiap.mottu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.mottu.dto.AuthResponse;
import com.fiap.mottu.dto.CadastroRequest;
import com.fiap.mottu.dto.LoginRequest;
import com.fiap.mottu.entity.MottuUsuario;
import com.fiap.mottu.service.JwtService;
import com.fiap.mottu.service.MottuUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private MottuUsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastroRequest request) {
        try {
            MottuUsuario usuario = usuarioService.cadastrarUsuario(request);
            String token = jwtService.generateToken(usuario);
            
            AuthResponse response = new AuthResponse(
                token,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().name()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no cadastro: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
            );
            
            MottuUsuario usuario = usuarioService.buscarPorEmail(request.getEmail());
            String token = jwtService.generateToken(usuario);
            
            AuthResponse response = new AuthResponse(
                token,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().name()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> obterPerfil(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String email = jwtService.extractUsername(jwt);
            MottuUsuario usuario = usuarioService.buscarPorEmail(email);
            
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Token inválido");
        }
    }
}
