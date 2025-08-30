package com.fiap.mottu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO: CadastroRequest
 * 
 * Este DTO é usado para receber os dados de cadastro de novos usuários
 * através de requisições HTTP (formulário de cadastro).
 * 
 * FUNÇÃO: Captura dados para criar nova conta de usuário
 * VALIDAÇÃO: Bean Validation para garantir dados corretos
 * SEGURANÇA: Validações de tamanho e formato para segurança
 */
public class CadastroRequest {

    /**
     * NOME COMPLETO DO USUÁRIO
     * 
     * VALIDAÇÕES:
     * - @NotBlank: Não pode ser vazio ou só espaços
     * - @Size: Deve ter entre 2 e 100 caracteres
     * 
     * IMPORTANTE: 
     * - Mínimo 2: Evita nomes muito curtos
     * - Máximo 100: Limita tamanho no banco de dados
     * - Usado para identificação do usuário
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    /**
     * EMAIL DO USUÁRIO (USADO COMO LOGIN)
     * 
     * VALIDAÇÕES:
     * - @NotBlank: Não pode ser vazio
     * - @Email: Deve ter formato de email válido
     * 
     * IMPORTANTE: 
     * - Este email será usado como username para login
     * - Deve ser único no sistema
     * - Spring Security usará para autenticação
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    /**
     * SENHA DO USUÁRIO
     * 
     * VALIDAÇÕES:
     * - @NotBlank: Não pode ser vazio
     * - @Size: Mínimo 6 caracteres por segurança
     * 
     * SEGURANÇA:
     * - Mínimo 6: Evita senhas muito fracas
     * - Será criptografada pelo Spring Security
     * - Nunca armazenada em texto puro
     * 
     * NOTA: Em produção, considerar validações mais rigorosas
     * como: maiúsculas, minúsculas, números, caracteres especiais
     */
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String senha;

    // ========================================
    // CONSTRUTORES
    // ========================================
    
    /**
     * CONSTRUTOR PADRÃO
     * - Obrigatório para deserialização JSON
     * - Spring Boot usa para criar instâncias a partir de requisições
     * - Necessário para o funcionamento correto da aplicação
     */
    public CadastroRequest() {}

    /**
     * CONSTRUTOR COM PARÂMETROS
     * - Usado para criar instâncias programaticamente
     * - Útil para testes unitários e integração
     * - Permite criação rápida de objetos para validação
     */
    public CadastroRequest(String nome, String email, String senha) {
        this.nome = nome;
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
     * IMPORTANTE: 
     * - Spring Boot usa reflection para acessar estes métodos
     * - Necessários para deserialização JSON
     * - Usados pelo Bean Validation durante a validação
     */
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
