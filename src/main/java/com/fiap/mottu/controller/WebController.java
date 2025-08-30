package com.fiap.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * CONTROLLER: WebController
 * 
 * Este controller gerencia as páginas web da aplicação Mottu.
 * Responsável por mapear URLs para templates Thymeleaf,
 * fornecendo a interface de usuário da aplicação.
 * 
 * FUNÇÃO: Mapeamento de páginas web
 * TECNOLOGIA: Spring Boot + Thymeleaf
 * RETORNO: Nomes de templates (não JSON)
 * NAVEGAÇÃO: Páginas principais da aplicação
 * 
 * ANOTAÇÕES IMPORTANTES:
 * - @Controller: Controller web (retorna nomes de templates)
 * - @GetMapping: Mapeia requisições GET para métodos
 * - Diferente de @RestController (que retorna JSON)
 * 
 * ARQUITETURA:
 * - Controller simples para páginas estáticas
 * - Integra com Spring Security para controle de acesso
 * - Templates Thymeleaf são renderizados no servidor
 * - Frontend recebe HTML completo
 */
@Controller
public class WebController {

    /**
     * ENDPOINT: GET /
     * 
     * FUNÇÃO: Página inicial da aplicação
     * RETORNO: Template "home" (home.html)
     * ACESSO: Público (permitido para todos)
     * 
     * IMPORTANTE: 
     * - Primeira página vista pelos usuários
     * - Pode conter informações sobre o sistema
     * - Links para login e cadastro
     * - Não requer autenticação
     * 
     * TEMPLATE: home.html
     * - Localização: src/main/resources/templates/
     * - Renderizado pelo Thymeleaf
     * - Pode incluir fragments (header, footer)
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * ENDPOINT: GET /login
     * 
     * FUNÇÃO: Página de login de usuários
     * RETORNO: Template "login" (login.html)
     * ACESSO: Público (permitido para todos)
     * 
     * IMPORTANTE: 
     * - Formulário de autenticação
     * - Campos: email e senha
     * - Validação no frontend e backend
     * - Redireciona para dashboard após login
     * 
     * SEGURANÇA:
     * - Endpoint público (não requer autenticação)
     * - Usuários já logados podem acessar
     * - Formulário envia dados para /api/auth/login
     * 
     * TEMPLATE: login.html
     * - Formulário de login responsivo
     * - Validação JavaScript
     * - Integração com API de autenticação
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * ENDPOINT: GET /cadastro
     * 
     * FUNÇÃO: Página de cadastro de novos usuários
     * RETORNO: Template "cadastro" (cadastro.html)
     * ACESSO: Público (permitido para todos)
     * 
     * IMPORTANTE: 
     * - Formulário de criação de conta
     * - Campos: nome, email, senha, confirmação de senha
     * - Validação no frontend e backend
     * - Redireciona para dashboard após cadastro
     * 
     * VALIDAÇÕES:
     * - Nome: 2-100 caracteres
     * - Email: formato válido e único
     * - Senha: mínimo 6 caracteres
     * - Confirmação: deve ser igual à senha
     * 
     * TEMPLATE: cadastro.html
     * - Formulário de cadastro responsivo
     * - Validação em tempo real
     * - Integração com API de cadastro
     */
    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    /**
     * ENDPOINT: GET /dashboard
     * 
     * FUNÇÃO: Dashboard principal do usuário
     * RETORNO: Template "dashboard" (dashboard.html)
     * ACESSO: Usuários autenticados (qualquer perfil)
     * 
     * IMPORTANTE: 
     * - Página principal após login
     * - Mostra informações do usuário logado
     * - Menu de navegação
     * - Ações rápidas (atualizar perfil, ver token)
     * 
     * SEGURANÇA:
     * - Endpoint protegido (requer autenticação)
     * - Acesso controlado pelo Spring Security
     * - Usuários não logados são redirecionados
     * 
     * FUNCIONALIDADES:
     * - Exibir dados do usuário
     * - Mostrar token JWT atual
     * - Links para outras páginas
     * - Opção de logout
     * 
     * TEMPLATE: dashboard.html
     * - Layout responsivo
     * - Informações do usuário
     * - Navegação principal
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * ENDPOINT: GET /admin
     * 
     * FUNÇÃO: Área administrativa do sistema
     * RETORNO: Template "admin" (admin.html)
     * ACESSO: Apenas usuários com perfil ADMIN
     * 
     * IMPORTANTE: 
     * - Página restrita a administradores
     * - Funcionalidades de gestão do sistema
     * - Controle de usuários
     * - Estatísticas e relatórios
     * 
     * SEGURANÇA:
     * - Endpoint protegido (requer autenticação)
     * - Acesso restrito a usuários com role ADMIN
     * - Spring Security controla acesso
     * - Usuários comuns recebem erro 403
     * 
     * FUNCIONALIDADES ADMIN:
     * - Listar todos os usuários
     * - Alterar perfis de usuários
     * - Ativar/desativar contas
     * - Visualizar logs do sistema
     * - Estatísticas de uso
     * 
     * TEMPLATE: admin.html
     * - Interface administrativa
     * - Tabelas de usuários
     * - Formulários de gestão
     * - Dashboard administrativo
     */
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
