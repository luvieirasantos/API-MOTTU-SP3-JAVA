-- V1__Criar_Tabela_Usuarios.sql
-- Criação da tabela de usuários do sistema Mottu

CREATE TABLE mottu_usuarios_sistema (
  id_usuario NUMBER(19) NOT NULL,
  nome_completo VARCHAR2(100) NOT NULL,
  email_usuario VARCHAR2(100) NOT NULL UNIQUE,
  senha_criptografada VARCHAR2(255) NOT NULL,
  perfil_acesso VARCHAR2(20) DEFAULT 'USUARIO' NOT NULL,
  ativo NUMBER(1) DEFAULT 1 CHECK (ativo IN (0,1)),
  data_criacao TIMESTAMP(6) DEFAULT SYSTIMESTAMP,
  data_atualizacao TIMESTAMP(6),
  CONSTRAINT PK_MOTTU_USUARIOS_SISTEMA PRIMARY KEY (id_usuario)
);

-- Sequence para PK
CREATE SEQUENCE SEQ_MOTTU_USUARIOS_SISTEMA START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- Trigger para autoincremento
CREATE OR REPLACE TRIGGER TRG_MOTTU_USUARIOS_BI
BEFORE INSERT ON mottu_usuarios_sistema
FOR EACH ROW
WHEN (NEW.id_usuario IS NULL)
BEGIN
  SELECT SEQ_MOTTU_USUARIOS_SISTEMA.NEXTVAL INTO :NEW.id_usuario FROM DUAL;
END;
/

-- Trigger para updated_at
CREATE OR REPLACE TRIGGER TRG_MOTTU_USUARIOS_BU
BEFORE UPDATE ON mottu_usuarios_sistema
FOR EACH ROW
BEGIN
  :NEW.data_atualizacao := SYSTIMESTAMP;
END;
/

-- Índices para melhorar performance
CREATE INDEX idx_mottu_usuarios_email ON mottu_usuarios_sistema(email_usuario);
CREATE INDEX idx_mottu_usuarios_ativo ON mottu_usuarios_sistema(ativo);
CREATE INDEX idx_mottu_usuarios_perfil ON mottu_usuarios_sistema(perfil_acesso);

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
