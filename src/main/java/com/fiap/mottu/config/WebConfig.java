package com.fiap.mottu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * CONFIGURAÇÃO: WebConfig
 * 
 * Esta classe configura beans relacionados à web e autenticação
 * que são usados pelo Spring Security. Define como as senhas
 * são criptografadas e como a autenticação é processada.
 * 
 * FUNÇÃO: Configuração de beans de autenticação
 * TECNOLOGIA: Spring Security + BCrypt
 * INTEGRAÇÃO: MottuUsuarioService (UserDetailsService)
 * 
 * ANOTAÇÕES IMPORTANTES:
 * - @Configuration: Marca como classe de configuração
 * - @Bean: Define beans para o Spring gerenciar
 */
@Configuration
public class WebConfig {

    /**
     * BEAN: PasswordEncoder
     * 
     * FUNÇÃO: Criptografia e verificação de senhas
     * IMPLEMENTAÇÃO: BCryptPasswordEncoder
     * ALGORITMO: BCrypt (Blowfish Crypt)
     * 
     * IMPORTANTE: 
     * - BCrypt é o padrão de segurança para senhas
     * - Gera hash único para cada senha
     * - Permite verificação sem descriptografar
     * - Integra automaticamente com Spring Security
     * 
     * CARACTERÍSTICAS BCrypt:
     * - Salt automático (evita ataques rainbow table)
     * - Configurável (número de rounds)
     * - Lento por design (protege contra força bruta)
     * 
     * USO:
     * - Criptografar senhas durante cadastro
     * - Verificar senhas durante login
     * - Spring Security usa automaticamente
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * BEAN: AuthenticationProvider
     * 
     * FUNÇÃO: Provedor de autenticação para Spring Security
     * IMPLEMENTAÇÃO: DaoAuthenticationProvider
     * INTEGRAÇÃO: MottuUsuarioService + PasswordEncoder
     * 
     * IMPORTANTE: 
     * - Conecta Spring Security com nossa lógica de usuários
     * - Usa MottuUsuarioService para buscar usuários
     * - Usa BCrypt para verificar senhas
     * 
     * FLUXO DE AUTENTICAÇÃO:
     * 1. Usuário envia email/senha
     * 2. Spring Security chama este provider
     * 3. Provider busca usuário via MottuUsuarioService
     * 4. Provider verifica senha via PasswordEncoder
     * 5. Se válido: usuário autenticado
     * 6. Se inválido: exceção de autenticação
     * 
     * PARÂMETROS:
     * - userDetailsService: MottuUsuarioService (implementa UserDetailsService)
     * - passwordEncoder: BCryptPasswordEncoder (bean definido acima)
     */
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        
        // CRIAÇÃO: Provedor de autenticação baseado em DAO
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        
        // CONFIGURAÇÃO: Serviço de usuários (MottuUsuarioService)
        authProvider.setUserDetailsService(userDetailsService);
        
        // CONFIGURAÇÃO: Encoder de senhas (BCrypt)
        authProvider.setPasswordEncoder(passwordEncoder);
        
        return authProvider;
    }

    /**
     * BEAN: AuthenticationManager
     * 
     * FUNÇÃO: Gerenciador principal de autenticação
     * IMPLEMENTAÇÃO: Configuração automática do Spring Security
     * USO: Controllers que precisam autenticar usuários
     * 
     * IMPORTANTE: 
     * - Bean padrão do Spring Security
     * - Gerencia todo o processo de autenticação
     * - Usa AuthenticationProvider configurado acima
     * 
     * USOS COMUNS:
     * - Login programático
     * - Validação de credenciais
     * - Autenticação em testes
     * 
     * INTEGRAÇÃO:
     * - Usa AuthenticationProvider configurado
     * - Conecta com MottuUsuarioService
     * - Gerencia tokens de autenticação
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
