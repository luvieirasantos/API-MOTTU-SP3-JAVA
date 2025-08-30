package com.fiap.mottu.dto;

/**
 * DTO: AuthResponse
 * 
 * Este DTO é usado para retornar a resposta de autenticação
 * após um login bem-sucedido. Contém todas as informações
 * necessárias para o frontend gerenciar a sessão do usuário.
 * 
 * FUNÇÃO: Resposta de sucesso para login/cadastro
 * SEGURANÇA: Contém token JWT para autenticação
 * FRONTEND: Usado para configurar sessão e redirecionamentos
 */
public class AuthResponse {

    /**
     * TOKEN JWT
     * 
     * FUNÇÃO: Token de autenticação para requisições subsequentes
     * FORMATO: String longa com informações criptografadas
     * SEGURANÇA: Deve ser enviado no header Authorization
     * 
     * IMPORTANTE: Este token prova que o usuário está autenticado
     * e contém informações sobre permissões e tempo de expiração
     */
    private String token;

    /**
     * TIPO DO TOKEN
     * 
     * VALOR PADRÃO: "Bearer" (padrão OAuth2/JWT)
     * FUNÇÃO: Indica como o token deve ser usado
     * FORMATO HEADER: "Authorization: Bearer <token>"
     * 
     * NOTA: "Bearer" significa que quem possui o token
     * tem autorização para acessar os recursos
     */
    private String tipo = "Bearer";

    /**
     * NOME DO USUÁRIO
     * 
     * FUNÇÃO: Nome para exibição no frontend
     * USO: Dashboard, cabeçalho, mensagens de boas-vindas
     * 
     * IMPORTANTE: Não é informação sensível, pode ser exibida
     */
    private String nome;

    /**
     * EMAIL DO USUÁRIO
     * 
     * FUNÇÃO: Email para identificação e exibição
     * USO: Dashboard, perfil do usuário, identificação
     * 
     * IMPORTANTE: Mesmo email usado para login
     */
    private String email;

    /**
     * PERFIL DO USUÁRIO
     * 
     * FUNÇÃO: Define permissões e acesso no frontend
     * VALORES: "ADMIN" ou "USUARIO"
     * USO: Controle de menu, redirecionamentos, funcionalidades
     * 
     * IMPORTANTE: Frontend usa para mostrar/esconder elementos
     * baseado no perfil do usuário logado
     */
    private String perfil;

    // ========================================
    // CONSTRUTORES
    // ========================================
    
    /**
     * CONSTRUTOR PADRÃO
     * - Obrigatório para serialização JSON
     * - Spring Boot usa para criar instâncias
     * - Necessário para funcionamento correto
     */
    public AuthResponse() {}

    /**
     * CONSTRUTOR COM PARÂMETROS PRINCIPAIS
     * - Usado para criar resposta de autenticação
     * - Define todos os dados necessários para o frontend
     * - Tipo é definido automaticamente como "Bearer"
     * 
     * PARÂMETROS:
     * - token: JWT gerado pelo sistema
     * - nome: Nome do usuário autenticado
     * - email: Email do usuário
     * - perfil: Perfil de acesso (ADMIN/USUARIO)
     */
    public AuthResponse(String token, String nome, String email, String perfil) {
        this.token = token;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }

    // ========================================
    // GETTERS E SETTERS
    // ========================================
    
    /**
     * GETTERS: Permitem acesso aos campos privados
     * SETTERS: Permitem modificação dos campos
     * 
     * IMPORTANTE: 
     * - Spring Boot usa reflection para serialização JSON
     * - Frontend recebe estes dados para configurar sessão
     * - Todos os campos são necessários para funcionamento
     */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
