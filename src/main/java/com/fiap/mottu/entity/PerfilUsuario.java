package com.fiap.mottu.entity;

/**
 * ENUM: PerfilUsuario
 * 
 * Este enum define os diferentes tipos de usuários no sistema Mottu.
 * Cada perfil tem permissões e acessos específicos.
 * 
 * IMPORTANTE: Os nomes dos perfis são usados pelo Spring Security
 * para criar as autoridades (ROLE_ADMIN, ROLE_USUARIO)
 */
public enum PerfilUsuario {
    
    /**
     * PERFIL ADMIN
     * 
     * PERMISSÕES:
     * - Acesso total ao sistema
     * - Pode ver todos os usuários
     * - Pode gerenciar perfis
     * - Acesso à área administrativa
     * 
     * USO: Administradores do sistema, professores, coordenadores
     */
    ADMIN,
    
    /**
     * PERFIL USUARIO
     * 
     * PERMISSÕES:
     * - Acesso limitado ao sistema
     * - Pode ver apenas seu próprio perfil
     * - Acesso ao dashboard básico
     * 
     * USO: Alunos, usuários comuns do sistema
     * 
     * NOTA: Este é o perfil padrão para novos usuários
     */
    USUARIO
}
