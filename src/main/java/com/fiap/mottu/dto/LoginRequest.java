package com.fiap.mottu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO: LoginRequest
 * 
 * Este DTO (Data Transfer Object) é usado para receber os dados
 * de login do usuário através de requisições HTTP.
 * 
 * FUNÇÃO: Captura os dados do formulário de login
 * VALIDAÇÃO: Usa Bean Validation para garantir dados corretos
 * SEGURANÇA: Não armazena dados sensíveis permanentemente
 */
public class LoginRequest {

    /**
     * EMAIL DO USUÁRIO (USADO COMO LOGIN)
     * 
     * VALIDAÇÕES:
     * - @NotBlank: Não pode ser vazio ou só espaços
     * - @Email: Deve ter formato de email válido
     * 
     * IMPORTANTE: Este campo é usado como username para autenticação
     * no Spring Security
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    /**
     * SENHA DO USUÁRIO
     * 
     * VALIDAÇÕES:
     * - @NotBlank: Não pode ser vazio
     * 
     * SEGURANÇA: 
     * - A senha será criptografada pelo Spring Security
     * - Nunca é armazenada em texto puro
     * - Usada apenas para autenticação
     */
    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    // ========================================
    // CONSTRUTORES
    // ========================================
    
    /**
     * CONSTRUTOR PADRÃO
     * - Obrigatório para deserialização JSON
     * - Spring Boot usa para criar instâncias a partir de requisições
     */
    public LoginRequest() {}

    /**
     * CONSTRUTOR COM PARÂMETROS
     * - Usado para criar instâncias programaticamente
     * - Útil para testes unitários
     */
    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // ========================================
    // GETTERS E SETTERS
    // ========================================
    
    /**
     * GETTERS: Permitem acesso aos campos privados
     * SETTERS: Permitem modificação dos campos
     * 
     * IMPORTANTE: Spring Boot usa reflection para acessar estes métodos
     * durante a deserialização JSON e validação
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
