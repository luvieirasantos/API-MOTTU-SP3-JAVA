package com.fiap.mottu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fiap.mottu.security.JwtAuthenticationFilter;

/**
 * CONFIGURAÇÃO: SecurityConfig
 * 
 * Esta classe configura toda a segurança da aplicação Mottu.
 * Define como as requisições são autenticadas, autorizadas e
 * quais endpoints são públicos ou protegidos.
 * 
 * FUNÇÃO: Configuração central de segurança
 * TECNOLOGIA: Spring Security 6.x
 * AUTENTICAÇÃO: JWT (stateless)
 * AUTORIZAÇÃO: Baseada em roles/perfis
 * 
 * ANOTAÇÕES IMPORTANTES:
 * - @Configuration: Marca como classe de configuração
 * - @EnableWebSecurity: Habilita Spring Security
 * - @EnableMethodSecurity: Habilita @PreAuthorize, @Secured
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * FILTRO JWT
     * 
     * FUNÇÃO: Intercepta requisições e valida tokens JWT
     * INJEÇÃO: @Autowired para injeção de dependência
     * COMPORTAMENTO: Executa antes da autenticação padrão
     * 
     * IMPORTANTE: 
     * - Filtro customizado para autenticação JWT
     * - Executa antes do UsernamePasswordAuthenticationFilter
     * - Responsável por configurar contexto de segurança
     */
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    /**
     * PROVEDOR DE AUTENTICAÇÃO
     * 
     * FUNÇÃO: Valida credenciais durante login
     * INJEÇÃO: @Autowired para injeção de dependência
     * IMPLEMENTAÇÃO: Definida em WebConfig
     * 
     * IMPORTANTE: 
     * - Usado para autenticação de login
     * - Valida email/senha contra banco de dados
     * - Integra com MottuUsuarioService
     */
    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     * BEAN: SecurityFilterChain
     * 
     * FUNÇÃO: Configuração principal da cadeia de filtros de segurança
     * RETORNO: SecurityFilterChain configurado
     * USO: Spring Security usa esta configuração
     * 
     * IMPORTANTE: 
     * - Bean principal de configuração de segurança
     * - Define toda a política de segurança da aplicação
     * - Configura endpoints públicos e protegidos
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // ========================================
            // CONFIGURAÇÃO CSRF
            // ========================================
            
            /**
             * CSRF: Cross-Site Request Forgery
             * 
             * CONFIGURAÇÃO: csrf.disable()
             * FUNÇÃO: Desabilita proteção CSRF
             * 
             * JUSTIFICATIVA: 
             * - Aplicação usa JWT (stateless)
             * - Não há sessões para proteger
             * - Tokens JWT já fornecem proteção
             * 
             * IMPORTANTE: Em aplicações com sessões, manter CSRF habilitado
             */
            .csrf(csrf -> csrf.disable())
            
            // ========================================
            // CONFIGURAÇÃO DE AUTORIZAÇÃO
            // ========================================
            
            /**
             * AUTORIZAÇÃO: Define quais endpoints são públicos/protegidos
             * 
             * ESTRUTURA: authorizeHttpRequests()
             * FUNÇÃO: Controle de acesso baseado em URL
             * 
             * ENDPOINTS PÚBLICOS (permitAll):
             * - /api/auth/**: Endpoints de autenticação (login/cadastro)
             * - /: Página inicial
             * - /login: Página de login
             * - /cadastro: Página de cadastro
             * - /dashboard: Dashboard (acesso após login)
             * - /admin: Área administrativa (acesso após login)
             * - /css/**, /js/**, /images/**: Recursos estáticos
             * 
             * ENDPOINTS PROTEGIDOS:
             * - /api/admin/**: Apenas usuários com role ADMIN
             * - /api/user/**: Apenas usuários com role USUARIO
             * - anyRequest().authenticated(): Demais endpoints precisam de autenticação
             */
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/", "/login", "/cadastro", "/dashboard", "/admin", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasRole("USUARIO")
                .anyRequest().authenticated()
            )
            
            // ========================================
            // CONFIGURAÇÃO DE SESSÃO
            // ========================================
            
            /**
             * SESSÃO: Configuração de gerenciamento de sessão
             * 
             * POLÍTICA: SessionCreationPolicy.STATELESS
             * FUNÇÃO: Aplicação não cria sessões
             * 
             * IMPORTANTE: 
             * - Cada requisição é independente
             * - Autenticação via JWT em cada requisição
             * - Melhor para aplicações REST/API
             * - Escalabilidade horizontal
             */
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // ========================================
            // CONFIGURAÇÃO DE AUTENTICAÇÃO
            // ========================================
            
            /**
             * PROVEDOR: Configura provedor de autenticação
             * 
             * FUNÇÃO: authenticationProvider()
             * USO: Validação de credenciais durante login
             * 
             * IMPORTANTE: 
             * - Integra com MottuUsuarioService
             * - Valida email/senha contra banco
             * - Configurado em WebConfig
             */
            .authenticationProvider(authenticationProvider)
            
            // ========================================
            // CONFIGURAÇÃO DE FILTROS
            // ========================================
            
            /**
             * FILTRO: Adiciona filtro JWT antes da autenticação padrão
             * 
             * FUNÇÃO: addFilterBefore()
             * ORDEM: JwtAuthenticationFilter executa antes de UsernamePasswordAuthenticationFilter
             * 
             * IMPORTANTE: 
             * - Filtro JWT intercepta todas as requisições
             * - Configura autenticação se token válido
             * - Executa antes da validação padrão de credenciais
             * 
             * FLUXO:
             * 1. JwtAuthenticationFilter (valida JWT)
             * 2. UsernamePasswordAuthenticationFilter (valida credenciais)
             * 3. Controllers
             */
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ========================================
    // BEANS DEFINIDOS EM OUTRAS CLASSES
    // ========================================
    
    /**
     * PASSWORD ENCODER
     * 
     * LOCALIZAÇÃO: WebConfig
     * FUNÇÃO: Criptografia de senhas
     * ALGORITMO: BCrypt
     * 
     * IMPORTANTE: 
     * - Bean definido em WebConfig para organização
     * - Usado para criptografar senhas de usuários
     * - Integra com Spring Security automaticamente
     */
    // PasswordEncoder bean is defined in WebConfig

    /**
     * AUTHENTICATION PROVIDER
     * 
     * LOCALIZAÇÃO: WebConfig
     * FUNÇÃO: Validação de credenciais
     * IMPLEMENTAÇÃO: DaoAuthenticationProvider
     * 
     * IMPORTANTE: 
     * - Bean definido em WebConfig para organização
     * - Integra com MottuUsuarioService
     * - Configura como credenciais são validadas
     */
    // AuthenticationProvider bean is defined in WebConfig
}
