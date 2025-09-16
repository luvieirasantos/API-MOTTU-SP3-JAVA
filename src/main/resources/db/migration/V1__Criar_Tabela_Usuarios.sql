-- V1__Criar_Tabela_Usuarios.sql
-- Criação da tabela de usuários do sistema Mottu

CREATE TABLE IF NOT EXISTS mottu_usuarios_sistema (
    id_usuario SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    email_usuario VARCHAR(100) NOT NULL UNIQUE,
    senha_criptografada VARCHAR(255) NOT NULL,
    perfil_acesso VARCHAR(20) DEFAULT 'USUARIO' NOT NULL,
    ativo BOOLEAN DEFAULT TRUE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Índices para melhorar performance
CREATE INDEX IF NOT EXISTS idx_mottu_usuarios_email ON mottu_usuarios_sistema(email_usuario);
CREATE INDEX IF NOT EXISTS idx_mottu_usuarios_ativo ON mottu_usuarios_sistema(ativo);
CREATE INDEX IF NOT EXISTS idx_mottu_usuarios_perfil ON mottu_usuarios_sistema(perfil_acesso);

-- Comentários para documentação
COMMENT ON TABLE mottu_usuarios_sistema IS 'Tabela para armazenar usuários do sistema Mottu';
COMMENT ON COLUMN mottu_usuarios_sistema.id_usuario IS 'Identificador único do usuário';
COMMENT ON COLUMN mottu_usuarios_sistema.nome_completo IS 'Nome completo do usuário';
COMMENT ON COLUMN mottu_usuarios_sistema.email_usuario IS 'Email único do usuário para login';
COMMENT ON COLUMN mottu_usuarios_sistema.senha_criptografada IS 'Senha criptografada do usuário';
COMMENT ON COLUMN mottu_usuarios_sistema.perfil_acesso IS 'Perfil de acesso (ADMIN ou USUARIO)';
COMMENT ON COLUMN mottu_usuarios_sistema.ativo IS 'Flag indicando se o usuário está ativo ou inativo';
COMMENT ON COLUMN mottu_usuarios_sistema.data_criacao IS 'Data e hora de criação do registro';
COMMENT ON COLUMN mottu_usuarios_sistema.data_atualizacao IS 'Data e hora da última atualização';
