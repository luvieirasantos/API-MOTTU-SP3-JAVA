-- V1__Criar_Tabela_Usuarios.sql
-- Criação da tabela de usuários do sistema Mottu

BEGIN
  EXECUTE IMMEDIATE '
  CREATE TABLE mottu_usuarios_sistema (
    id_usuario NUMBER(19) NOT NULL,
    nome_completo VARCHAR2(100) NOT NULL,
    email_usuario VARCHAR2(100) NOT NULL UNIQUE,
    senha_criptografada VARCHAR2(255) NOT NULL,
    perfil_acesso VARCHAR2(20) DEFAULT ''USUARIO'' NOT NULL,
    ativo NUMBER(1) DEFAULT 1 CHECK (ativo IN (0,1)),
    data_criacao TIMESTAMP(6) DEFAULT SYSTIMESTAMP,
    data_atualizacao TIMESTAMP(6),
    CONSTRAINT PK_MOTTU_USUARIOS_SISTEMA PRIMARY KEY (id_usuario)
  )';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -955 THEN RAISE; END IF; -- ORA-00955: name is already used by an existing object
END;
/

-- Sequence para PK
DECLARE
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count FROM user_sequences WHERE sequence_name = 'SEQ_MOTTU_USUARIOS_SISTEMA';
  IF v_count = 0 THEN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE SEQ_MOTTU_USUARIOS_SISTEMA START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE';
  END IF;
END;
/

-- Trigger para autoincremento
DECLARE
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count FROM user_triggers WHERE trigger_name = 'TRG_MOTTU_USUARIOS_BI';
  IF v_count = 0 THEN
    EXECUTE IMMEDIATE '
      CREATE OR REPLACE TRIGGER TRG_MOTTU_USUARIOS_BI
      BEFORE INSERT ON mottu_usuarios_sistema
      FOR EACH ROW
      WHEN (NEW.id_usuario IS NULL)
      BEGIN
        SELECT SEQ_MOTTU_USUARIOS_SISTEMA.NEXTVAL INTO :NEW.id_usuario FROM DUAL;
      END;';
  END IF;
END;
/

-- Trigger para updated_at
DECLARE
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count FROM user_triggers WHERE trigger_name = 'TRG_MOTTU_USUARIOS_BU';
  IF v_count = 0 THEN
    EXECUTE IMMEDIATE '
      CREATE OR REPLACE TRIGGER TRG_MOTTU_USUARIOS_BU
      BEFORE UPDATE ON mottu_usuarios_sistema
      FOR EACH ROW
      BEGIN
        :NEW.data_atualizacao := SYSTIMESTAMP;
      END;';
  END IF;
END;
/

-- Índices para melhorar performance
DECLARE
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count FROM user_indexes WHERE index_name = 'IDX_MOTTU_USUARIOS_EMAIL';
  IF v_count = 0 THEN EXECUTE IMMEDIATE 'CREATE INDEX idx_mottu_usuarios_email ON mottu_usuarios_sistema(email_usuario)'; END IF;

  SELECT COUNT(*) INTO v_count FROM user_indexes WHERE index_name = 'IDX_MOTTU_USUARIOS_ATIVO';
  IF v_count = 0 THEN EXECUTE IMMEDIATE 'CREATE INDEX idx_mottu_usuarios_ativo ON mottu_usuarios_sistema(ativo)'; END IF;

  SELECT COUNT(*) INTO v_count FROM user_indexes WHERE index_name = 'IDX_MOTTU_USUARIOS_PERFIL';
  IF v_count = 0 THEN EXECUTE IMMEDIATE 'CREATE INDEX idx_mottu_usuarios_perfil ON mottu_usuarios_sistema(perfil_acesso)'; END IF;
END;
/

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
