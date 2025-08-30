package com.fiap.mottu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CLASSE PRINCIPAL: MottuAuthApplication
 * 
 * Esta é a classe principal da aplicação Mottu Auth API.
 * É o ponto de entrada (entry point) que inicia toda a
 * aplicação Spring Boot.
 * 
 * FUNÇÃO: Classe principal da aplicação
 * TECNOLOGIA: Spring Boot 3.2.0
 * JAVA: Versão 17 (LTS)
 * ARQUITETURA: Aplicação web com autenticação JWT
 * 
 * ANOTAÇÕES IMPORTANTES:
 * - @SpringBootApplication: Anotação principal do Spring Boot
 *   - @Configuration: Marca como classe de configuração
 *   - @EnableAutoConfiguration: Habilita auto-configuração
 *   - @ComponentScan: Escaneia componentes no pacote atual
 * 
 * COMPONENTES INCLUÍDOS:
 * - Entidades JPA (MottuUsuario, PerfilUsuario)
 * - DTOs (LoginRequest, CadastroRequest, AuthResponse)
 * - Repositórios (MottuUsuarioRepository)
 * - Serviços (MottuUsuarioService, JwtService)
 * - Controllers (AuthController, WebController)
 * - Configurações (SecurityConfig, WebConfig)
 * - Filtros de Segurança (JwtAuthenticationFilter)
 * 
 * ARQUITETURA DA APLICAÇÃO:
 * 
 * 1. CAMADA DE APRESENTAÇÃO:
 *    - Controllers REST (/api/auth/**)
 *    - Controllers Web (páginas HTML)
 *    - Templates Thymeleaf
 * 
 * 2. CAMADA DE SERVIÇOS:
 *    - Lógica de negócio
 *    - Autenticação e autorização
 *    - Geração e validação de JWT
 * 
 * 3. CAMADA DE DADOS:
 *    - Repositórios JPA
 *    - Entidades mapeadas para Oracle
 *    - Migrações Flyway
 * 
 * 4. SEGURANÇA:
 *    - Spring Security configurado
 *    - Autenticação JWT stateless
 *    - Autorização baseada em roles
 *    - Filtros de segurança customizados
 * 
 * CONFIGURAÇÕES AUTOMÁTICAS:
 * - Spring Security (segurança)
 * - Spring Data JPA (persistência)
 * - Thymeleaf (templates)
 * - Flyway (migrações de banco)
 * - Oracle JDBC (conexão com banco)
 * - BCrypt (criptografia de senhas)
 * - JJWT (tokens JWT)
 * 
 * BANCO DE DADOS:
 * - Oracle Database (FIAP)
 * - Tabela: MOTTU_USUARIOS_SISTEMA
 * - Migrações automáticas via Flyway
 * - Usuários padrão criados automaticamente
 * 
 * ENDPOINTS PRINCIPAIS:
 * - POST /api/auth/cadastro: Cadastro de usuários
 * - POST /api/auth/login: Autenticação de usuários
 * - GET /api/auth/perfil: Consulta de perfil
 * - GET /: Página inicial
 * - GET /login: Página de login
 * - GET /cadastro: Página de cadastro
 * - GET /dashboard: Dashboard do usuário
 * - GET /admin: Área administrativa
 * 
 * PERFIS DE USUÁRIO:
 * - ADMIN: Acesso total ao sistema
 * - USUARIO: Acesso limitado (padrão)
 * 
 * SEGURANÇA:
 * - Autenticação via JWT
 * - Senhas criptografadas com BCrypt
 * - Controle de acesso baseado em roles
 * - Endpoints protegidos e públicos
 * - Validação de dados com Bean Validation
 * 
 * DEPLOY:
 * - Dockerizado com Dockerfile multi-stage
 * - Configuração para Render
 * - Variáveis de ambiente configuráveis
 * - Health checks configurados
 */
@SpringBootApplication
public class MottuAuthApplication {

    /**
     * MÉTODO PRINCIPAL: main()
     * 
     * FUNÇÃO: Ponto de entrada da aplicação Java
     * EXECUÇÃO: Primeiro método executado
     * PARÂMETROS: args - argumentos da linha de comando
     * 
     * FLUXO DE INICIALIZAÇÃO:
     * 1. JVM executa este método
     * 2. SpringApplication.run() é chamado
     * 3. Spring Boot inicializa o contexto da aplicação
     * 4. Componentes são escaneados e instanciados
     * 5. Beans são configurados e injetados
     * 6. Aplicação fica pronta para receber requisições
     * 
     * IMPORTANTE: 
     * - Método estático (não precisa de instância)
     * - Spring Boot gerencia todo o ciclo de vida
     * - Aplicação roda até ser interrompida
     * - Logs de inicialização são exibidos no console
     * 
     * CONFIGURAÇÕES CARREGADAS:
     * - application.yml (configurações principais)
     * - application-prod.yml (configurações de produção)
     * - Beans definidos em @Configuration
     * - Componentes escaneados automaticamente
     * 
     * PORTA PADRÃO: 8080
     * CONTEXTO: / (raiz)
     * PROTOCOLO: HTTP/HTTPS
     */
    public static void main(String[] args) {
        // INICIALIZAÇÃO: Spring Boot inicia a aplicação
        SpringApplication.run(MottuAuthApplication.class, args);
        
        // APÓS EXECUÇÃO:
        // - Aplicação está rodando
        // - Endpoints estão disponíveis
        // - Banco de dados está conectado
        // - Segurança está configurada
        // - Logs mostram status da aplicação
    }
}
