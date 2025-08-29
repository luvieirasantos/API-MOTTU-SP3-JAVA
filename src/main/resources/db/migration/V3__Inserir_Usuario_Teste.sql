-- V3__Inserir_Usuario_Teste.sql
-- Inserção de usuário de teste para demonstração
-- Senha: user123 (criptografada com BCrypt)

INSERT INTO MOTTU_USUARIOS_SISTEMA (
    NOME_COMPLETO, 
    EMAIL_USUARIO, 
    SENHA_CRIPTOGRAFADA, 
    PERFIL_ACESSO, 
    ATIVO
) VALUES (
    'Usuário Teste',
    'user@mottu.com',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    'USUARIO',
    1
);
