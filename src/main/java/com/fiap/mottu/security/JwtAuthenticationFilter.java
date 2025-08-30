package com.fiap.mottu.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fiap.mottu.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * FILTRO: JwtAuthenticationFilter
 * 
 * Este filtro é responsável por interceptar todas as requisições HTTP
 * e verificar se existe um token JWT válido no header Authorization.
 * Se o token for válido, configura a autenticação no Spring Security.
 * 
 * FUNÇÃO: Autenticação automática via JWT
 * EXECUÇÃO: Uma vez por requisição (OncePerRequestFilter)
 * SEGURANÇA: Valida tokens antes de permitir acesso
 * INTEGRAÇÃO: Spring Security Context
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * SERVIÇO JWT
     * 
     * FUNÇÃO: Validação e extração de informações do token
     * INJEÇÃO: @Autowired para injeção de dependência
     * OPERAÇÕES: Extrair username, validar token, verificar expiração
     */
    @Autowired
    private JwtService jwtService;

    /**
     * SERVIÇO DE USUÁRIOS
     * 
     * FUNÇÃO: Carregar informações do usuário a partir do email
     * INJEÇÃO: @Autowired para injeção de dependência
     * IMPLEMENTAÇÃO: MottuUsuarioService (UserDetailsService)
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * MÉTODO: doFilterInternal()
     * 
     * IMPLEMENTAÇÃO: OncePerRequestFilter (Spring Security)
     * FUNÇÃO: Lógica principal do filtro executada a cada requisição
     * EXECUÇÃO: Antes de chegar aos controllers
     * 
     * FLUXO COMPLETO:
     * 1. Intercepta requisição HTTP
     * 2. Verifica header Authorization
     * 3. Extrai token JWT
     * 4. Valida token
     * 5. Configura autenticação se válido
     * 6. Continua para próximo filtro/controller
     * 
     * IMPORTANTE: 
     * - Executa antes de qualquer controller
     * - Configura contexto de segurança se token válido
     * - Permite acesso mesmo sem token (endpoints públicos)
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        
        // ========================================
        // EXTRAÇÃO DO HEADER AUTHORIZATION
        // ========================================
        
        /**
         * EXTRAÇÃO: Header Authorization da requisição
         * FORMATO ESPERADO: "Bearer <token_jwt>"
         * EXEMPLO: "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
         * 
         * IMPORTANTE: 
         * - Header pode ser null (usuário não autenticado)
         * - Deve começar com "Bearer " (espaço incluído)
         * - Token vem após os 7 primeiros caracteres
         */
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        
        // ========================================
        // VALIDAÇÃO INICIAL DO HEADER
        // ========================================
        
        /**
         * VERIFICAÇÃO: Se header existe e tem formato correto
         * 
         * CONDIÇÕES:
         * - authHeader != null: Header foi enviado
         * - authHeader.startsWith("Bearer "): Formato correto
         * 
         * COMPORTAMENTO:
         * - Se não atender condições: continua sem autenticação
         * - Se atender: processa token JWT
         * 
         * IMPORTANTE: 
         * - Permite acesso a endpoints públicos
         * - Não força autenticação em todas as requisições
         * - Usuários não logados podem acessar páginas públicas
         */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // ========================================
        // EXTRAÇÃO DO TOKEN E USERNAME
        // ========================================
        
        /**
         * EXTRAÇÃO: Token JWT do header
         * 
         * CÁLCULO: authHeader.substring(7)
         * - "Bearer " tem 7 caracteres
         * - Remove "Bearer " para obter apenas o token
         * 
         * EXEMPLO:
         * - Header: "Bearer abc123"
         * - Token: "abc123"
         */
        jwt = authHeader.substring(7);
        
        /**
         * EXTRAÇÃO: Email do usuário do token
         * 
         * FUNÇÃO: jwtService.extractUsername(jwt)
         * - Extrai subject (email) do token JWT
         * - Subject contém o email do usuário
         * - Usado para buscar usuário no banco
         */
        userEmail = jwtService.extractUsername(jwt);
        
        // ========================================
        // VALIDAÇÃO E CONFIGURAÇÃO DE AUTENTICAÇÃO
        // ========================================
        
        /**
         * VERIFICAÇÃO: Se pode configurar autenticação
         * 
         * CONDIÇÕES:
         * - userEmail != null: Token válido e username extraído
         * - SecurityContextHolder.getContext().getAuthentication() == null: Usuário ainda não autenticado
         * 
         * IMPORTANTE: 
         * - Evita reconfiguração desnecessária
         * - Usuário já autenticado não é processado novamente
         * - Otimização de performance
         */
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            /**
             * CARREGAMENTO: Dados do usuário do banco
             * 
             * FUNÇÃO: userDetailsService.loadUserByUsername(userEmail)
             * - Busca usuário por email
             * - Retorna UserDetails (MottuUsuario)
             * - Inclui permissões e status da conta
             */
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            
            /**
             * VALIDAÇÃO: Se token é válido para o usuário
             * 
             * FUNÇÃO: jwtService.validateToken(jwt, userDetails)
             * - Verifica assinatura do token
             * - Confirma que não expirou
             * - Valida se username corresponde ao usuário
             * 
             * IMPORTANTE: 
             * - Validação completa de segurança
             * - Token inválido não configura autenticação
             * - Usuário permanece não autenticado
             */
            if (jwtService.validateToken(jwt, userDetails)) {
                
                /**
                 * CRIAÇÃO: Token de autenticação do Spring Security
                 * 
                 * PARÂMETROS:
                 * - userDetails: Dados do usuário (MottuUsuario)
                 * - null: Credenciais (não necessárias para JWT)
                 * - userDetails.getAuthorities(): Permissões do usuário
                 * 
                 * FUNÇÃO: 
                 * - Representa usuário autenticado
                 * - Contém permissões para controle de acesso
                 * - Usado pelo Spring Security para autorização
                 */
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
                
                /**
                 * CONFIGURAÇÃO: Detalhes da requisição
                 * 
                 * FUNÇÃO: setDetails()
                 * - Adiciona informações da requisição HTTP
                 * - IP, User-Agent, sessão, etc.
                 * - Útil para auditoria e logs
                 */
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                /**
                 * CONFIGURAÇÃO: Contexto de segurança
                 * 
                 * FUNÇÃO: SecurityContextHolder.getContext().setAuthentication()
                 * - Define usuário como autenticado
                 * - Disponibiliza informações para toda a requisição
                 * - Permite uso de @PreAuthorize, @Secured, etc.
                 * 
                 * IMPORTANTE: 
                 * - Usuário agora está autenticado
                 * - Controllers podem acessar dados do usuário
                 * - Spring Security permite acesso baseado em permissões
                 */
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // ========================================
        // CONTINUAÇÃO DO FILTRO
        // ========================================
        
        /**
         * CONTINUAÇÃO: Para próximo filtro ou controller
         * 
         * FUNÇÃO: filterChain.doFilter()
         * - Continua execução da cadeia de filtros
         * - Se autenticado: usuário tem acesso baseado em permissões
         * - Se não autenticado: acesso apenas a endpoints públicos
         * 
         * IMPORTANTE: 
         * - Sempre executa, independente de autenticação
         * - Permite que requisição continue para controller
         * - Spring Security controla acesso final
         */
        filterChain.doFilter(request, response);
    }
}
