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

/**
 * CONTROLLER: AuthController
 * 
 * Este controller gerencia todas as operações de autenticação
 * da aplicação Mottu. Responsável por cadastro, login e
 * consulta de perfil de usuários.
 * 
 * FUNÇÃO: Endpoints de autenticação e autorização
 * TECNOLOGIA: Spring Boot REST Controller
 * SEGURANÇA: Validação de dados, geração de JWT
 * ENDPOINTS: /api/auth/**
 * 
 * ANOTAÇÕES IMPORTANTES:
 * - @RestController: Controller REST (retorna JSON)
 * - @RequestMapping("/api/auth"): Base path para todos os endpoints
 * - @CrossOrigin(origins = "*"): Permite CORS de qualquer origem
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    /**
     * SERVIÇO DE USUÁRIOS
     * 
     * FUNÇÃO: Lógica de negócio para usuários
     * INJEÇÃO: @Autowired para injeção de dependência
     * OPERAÇÕES: Cadastro, busca, validação de senhas
     */
    @Autowired
    private MottuUsuarioService usuarioService;

    /**
     * SERVIÇO JWT
     * 
     * FUNÇÃO: Geração e validação de tokens JWT
     * INJEÇÃO: @Autowired para injeção de dependência
     * OPERAÇÕES: Gerar token, extrair username, validar token
     */
    @Autowired
    private JwtService jwtService;

    /**
     * GERENCIADOR DE AUTENTICAÇÃO
     * 
     * FUNÇÃO: Valida credenciais durante login
     * INJEÇÃO: @Autowired para injeção de dependência
     * IMPLEMENTAÇÃO: Configurado em WebConfig
     * 
     * IMPORTANTE: 
     * - Usado para autenticar usuários durante login
     * - Integra com Spring Security
     * - Valida email/senha contra banco de dados
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * ENDPOINT: POST /api/auth/cadastro
     * 
     * FUNÇÃO: Cadastra novo usuário no sistema
     * RETORNO: AuthResponse com token JWT e dados do usuário
     * VALIDAÇÃO: @Valid para validar dados do request
     * 
     * FLUXO COMPLETO:
     * 1. Recebe dados de cadastro (nome, email, senha)
     * 2. Valida dados com Bean Validation
     * 3. Cria usuário via MottuUsuarioService
     * 4. Gera token JWT para o novo usuário
     * 5. Retorna resposta com token e dados
     * 
     * IMPORTANTE: 
     * - Usuário é criado com perfil USUARIO (padrão)
     * - Senha é criptografada automaticamente
     * - Token é gerado imediatamente após cadastro
     * - Usuário pode fazer login imediatamente
     * 
     * TRATAMENTO DE ERRO:
     * - Captura exceções e retorna erro 400
     * - Mensagem de erro é retornada ao frontend
     * - Validações de duplicação são tratadas
     */
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastroRequest request) {
        try {
            // CADASTRO: Cria novo usuário no sistema
            MottuUsuario usuario = usuarioService.cadastrarUsuario(request);
            
            // TOKEN: Gera JWT para o usuário cadastrado
            String token = jwtService.generateToken(usuario);
            
            // RESPOSTA: Cria objeto de resposta com token e dados
            AuthResponse response = new AuthResponse(
                token,                    // Token JWT gerado
                usuario.getNome(),        // Nome do usuário
                usuario.getEmail(),       // Email do usuário
                usuario.getPerfil().name() // Perfil (ADMIN/USUARIO)
            );
            
            // RETORNO: Resposta de sucesso com dados
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // ERRO: Retorna erro 400 com mensagem
            return ResponseEntity.badRequest().body("Erro no cadastro: " + e.getMessage());
        }
    }

    /**
     * ENDPOINT: POST /api/auth/login
     * 
     * FUNÇÃO: Autentica usuário e retorna token JWT
     * RETORNO: AuthResponse com token e dados do usuário
     * VALIDAÇÃO: @Valid para validar dados do request
     * 
     * FLUXO COMPLETO:
     * 1. Recebe credenciais (email, senha)
     * 2. Valida dados com Bean Validation
     * 3. Autentica via Spring Security (AuthenticationManager)
     * 4. Busca dados completos do usuário
     * 5. Gera novo token JWT
     * 6. Retorna resposta com token e dados
     * 
     * IMPORTANTE: 
     * - Spring Security valida credenciais
     * - Senha é verificada via BCrypt
     * - Token é gerado apenas se autenticação for bem-sucedida
     * - Usuário deve estar ativo para fazer login
     * 
     * SEGURANÇA:
     * - Credenciais são validadas pelo Spring Security
     * - Integra com MottuUsuarioService (UserDetailsService)
     * - Usa BCrypt para verificação de senhas
     * 
     * TRATAMENTO DE ERRO:
     * - Captura exceções de autenticação
     * - Retorna erro 400 com mensagem genérica
     * - Não revela se email ou senha estão incorretos
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            // AUTENTICAÇÃO: Valida credenciais via Spring Security
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
            );
            
            // USUÁRIO: Busca dados completos do usuário autenticado
            MottuUsuario usuario = usuarioService.buscarPorEmail(request.getEmail());
            
            // TOKEN: Gera novo JWT para o usuário
            String token = jwtService.generateToken(usuario);
            
            // RESPOSTA: Cria objeto de resposta com token e dados
            AuthResponse response = new AuthResponse(
                token,                    // Token JWT gerado
                usuario.getNome(),        // Nome do usuário
                usuario.getEmail(),       // Email do usuário
                usuario.getPerfil().name() // Perfil (ADMIN/USUARIO)
            );
            
            // RETORNO: Resposta de sucesso com dados
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // ERRO: Retorna erro 400 com mensagem genérica
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }
    }

    /**
     * ENDPOINT: GET /api/auth/perfil
     * 
     * FUNÇÃO: Retorna dados do usuário autenticado
     * RETORNO: MottuUsuario com dados do perfil
     * AUTENTICAÇÃO: Requer token JWT válido no header
     * 
     * FLUXO COMPLETO:
     * 1. Recebe token JWT no header Authorization
     * 2. Remove prefixo "Bearer " do token
     * 3. Extrai email do usuário do token
     * 4. Busca dados completos do usuário
     * 5. Retorna dados do perfil
     * 
     * IMPORTANTE: 
     * - Endpoint protegido (requer autenticação)
     * - Token é validado automaticamente pelo filtro JWT
     * - Dados são extraídos do token, não do request body
     * - Usado para obter informações do usuário logado
     * 
     * SEGURANÇA:
     * - Token é validado pelo JwtAuthenticationFilter
     * - Usuário deve estar autenticado
     * - Dados são retornados apenas para o próprio usuário
     * 
     * TRATAMENTO DE ERRO:
     * - Captura exceções de token inválido
     * - Retorna erro 400 se token for inválido
     * - Token expirado ou malformado gera erro
     */
    @GetMapping("/perfil")
    public ResponseEntity<?> obterPerfil(@RequestHeader("Authorization") String token) {
        try {
            // TOKEN: Remove prefixo "Bearer " (7 caracteres)
            String jwt = token.substring(7);
            
            // USERNAME: Extrai email do usuário do token
            String email = jwtService.extractUsername(jwt);
            
            // USUÁRIO: Busca dados completos do usuário
            MottuUsuario usuario = usuarioService.buscarPorEmail(email);
            
            // RETORNO: Dados do perfil do usuário
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            // ERRO: Retorna erro 400 se token for inválido
            return ResponseEntity.badRequest().body("Token inválido");
        }
    }
}
