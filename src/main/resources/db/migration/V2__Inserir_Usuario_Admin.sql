-- V2__Inserir_Usuario_Admin.sql
-- Inserção do usuário administrador padrão
-- Senha: admin123 (criptografada com BCrypt)

INSERT INTO mottu_usuarios_sistema (
    nome_completo,
    email_usuario,
    senha_criptografada,
    perfil_acesso,
    ativo
) VALUES (
    'Administrador Sistema',
    'admin@mottu.com',
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa',
    'ADMIN',
    TRUE
);
