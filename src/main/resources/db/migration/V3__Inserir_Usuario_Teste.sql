-- V3__Inserir_Usuario_Teste.sql
-- Inserção de usuário de teste para demonstração
-- Senha: user123 (criptografada com BCrypt)

INSERT INTO mottu_usuarios_sistema (
    nome_completo,
    email_usuario,
    senha_criptografada,
    perfil_acesso,
    ativo
) VALUES (
    'Usuário Teste',
    'user@mottu.com',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    'USUARIO',
    TRUE
);
