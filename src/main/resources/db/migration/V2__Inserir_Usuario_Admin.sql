-- V2__Inserir_Usuario_Admin.sql
-- Inserção do usuário administrador padrão
-- Senha: admin123 (criptografada com BCrypt)

INSERT INTO MOTTU_USUARIOS_SISTEMA (
    NOME_COMPLETO, 
    EMAIL_USUARIO, 
    SENHA_CRIPTOGRAFADA, 
    PERFIL_ACESSO, 
    ATIVO
) VALUES (
    'Administrador Sistema',
    'admin@mottu.com',
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa',
    'ADMIN',
    1
);
