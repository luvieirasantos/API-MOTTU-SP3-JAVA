package com.fiap.mottu.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * CLASSE PRINCIPAL: MottuUsuario
 * 
 * Esta é a entidade principal que representa um usuário no sistema Mottu.
 * Ela implementa UserDetails do Spring Security, permitindo integração
 * direta com o sistema de autenticação e autorização.
 * 
 * ANOTAÇÕES IMPORTANTES:
 * - @Entity: Marca esta classe como uma entidade JPA (mapeamento para banco)
 * - @Table: Define o nome da tabela no banco Oracle
 * - implements UserDetails: Interface obrigatória para Spring Security
 */
@Entity
@Table(name = "mottu_usuarios_sistema")
public class MottuUsuario implements UserDetails {

    /**
     * ID ÚNICO DO USUÁRIO
     * - @Id: Marca este campo como chave primária
     * - @GeneratedValue: Gera automaticamente o ID (auto-incremento)
     * - @Column: Mapeia para coluna ID_USUARIO no banco
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mottu_usuarios")
    @SequenceGenerator(name = "seq_mottu_usuarios", sequenceName = "SEQ_MOTTU_USUARIOS_SISTEMA", allocationSize = 1)
    @Column(name = "id_usuario")
    private Long id;

    /**
     * NOME COMPLETO DO USUÁRIO
     * - @NotBlank: Valida que não pode ser vazio ou só espaços
     * - @Size: Define tamanho mínimo (2) e máximo (100) caracteres
     * - @Column: Mapeia para coluna NOME_COMPLETO, não pode ser nulo
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nome;

    /**
     * EMAIL DO USUÁRIO (USADO COMO LOGIN)
     * - @Email: Valida formato de email válido
     * - @Column: unique=true garante que cada email seja único no sistema
     * - Este campo será usado como username para login
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(name = "email_usuario", nullable = false, unique = true, length = 100)
    private String email;

    /**
     * SENHA CRIPTOGRAFADA
     * - @Size: Mínimo de 6 caracteres por segurança
     * - A senha será criptografada pelo Spring Security antes de salvar
     * - Nunca armazenamos senha em texto puro
     */
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    @Column(name = "senha_criptografada", nullable = false)
    private String senha;

    /**
     * PERFIL DE ACESSO (ENUM)
     * - @Enumerated(STRING): Salva o nome do enum como string no banco
     * - @Column: Mapeia para coluna PERFIL_ACESSO
     * - Valor padrão: USUARIO (usuário comum)
     * - Define as permissões e acesso do usuário no sistema
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_acesso", nullable = false)
    private PerfilUsuario perfil = PerfilUsuario.USUARIO;

    /**
     * STATUS DE ATIVAÇÃO DA CONTA
     * - @Column: Mapeia para coluna ATIVO
     * - Valor padrão: true (conta ativa)
     * - Usado para desativar contas sem deletá-las
     * - Importante para Spring Security (conta habilitada/desabilitada)
     */
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    // ========================================
    // CONSTRUTORES
    // ========================================
    
    /**
     * CONSTRUTOR PADRÃO (OBRIGATÓRIO PARA JPA)
     * - JPA precisa deste construtor para criar instâncias
     * - Sem parâmetros, usado para mapeamento automático
     */
    public MottuUsuario() {}

    /**
     * CONSTRUTOR COM PARÂMETROS PRINCIPAIS
     * - Usado para criar usuários rapidamente
     * - Não define perfil (usa padrão USUARIO)
     * - Não define status (usa padrão ativo=true)
     */
    public MottuUsuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // ========================================
    // GETTERS E SETTERS (OBRIGATÓRIOS PARA JPA)
    // ========================================
    
    /**
     * GETTERS: Permitem acesso aos campos privados
     * SETTERS: Permitem modificação dos campos
     * - JPA usa reflection para acessar estes métodos
     * - Spring Security também usa para autenticação
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    // ========================================
    // IMPLEMENTAÇÃO DO USERDETAILS (SPRING SECURITY)
    // ========================================
    
    /**
     * MÉTODO: getAuthorities()
     * 
     * RETORNA: Lista de permissões/autoridades do usuário
     * FUNÇÃO: Define o que o usuário pode fazer no sistema
     * FORMATO: "ROLE_" + nomeDoPerfil (ex: ROLE_ADMIN, ROLE_USUARIO)
     * 
     * IMPORTANTE: Spring Security usa estas autoridades para:
     * - @PreAuthorize("hasRole('ADMIN')")
     * - @Secured("ROLE_ADMIN")
     * - Controle de acesso às páginas/endpoints
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
    }

    /**
     * MÉTODO: getPassword()
     * 
     * RETORNA: Senha do usuário (para Spring Security)
     * FUNÇÃO: Spring Security compara com a senha digitada no login
     * IMPORTANTE: Retorna a senha criptografada, não a original
     */
    @Override
    public String getPassword() {
        return senha;
    }

    /**
     * MÉTODO: getUsername()
     * 
     * RETORNA: Email do usuário (usado como login)
     * FUNÇÃO: Spring Security usa para identificar o usuário
     * IMPORTANTE: No nosso sistema, username = email
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * MÉTODO: isAccountNonExpired()
     * 
     * RETORNA: true se a conta não expirou
     * FUNÇÃO: Controle de expiração de conta
     * IMPLEMENTAÇÃO: Sempre retorna true (contas não expiram)
     */
    @Override
    public boolean isAccountNonExpired() {
        return ativo;
    }

    /**
     * MÉTODO: isAccountNonLocked()
     * 
     * RETORNA: true se a conta não está bloqueada
     * FUNÇÃO: Controle de bloqueio de conta
     * IMPLEMENTAÇÃO: Retorna o status ativo (true=desbloqueada, false=bloqueada)
     */
    @Override
    public boolean isAccountNonLocked() {
        return ativo;
    }

    /**
     * MÉTODO: isCredentialsNonExpired()
     * 
     * RETORNA: true se as credenciais não expiraram
     * FUNÇÃO: Controle de expiração de senha
     * IMPLEMENTAÇÃO: Sempre retorna true (senhas não expiram)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return ativo;
    }

    /**
     * MÉTODO: isEnabled()
     * 
     * RETORNA: true se a conta está habilitada
     * FUNÇÃO: Controle principal de ativação da conta
     * IMPLEMENTAÇÃO: Retorna o status ativo
     * 
     * IMPORTANTE: Este é o método principal usado pelo Spring Security
     * para determinar se um usuário pode fazer login
     */
    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
