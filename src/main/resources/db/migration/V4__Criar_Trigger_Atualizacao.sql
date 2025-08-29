-- V4__Criar_Trigger_Atualizacao.sql
-- Criação de trigger para atualizar automaticamente a data de modificação

CREATE OR REPLACE TRIGGER TRG_MOTTU_USUARIOS_UPDATE
BEFORE UPDATE ON MOTTU_USUARIOS_SISTEMA
FOR EACH ROW
BEGIN
    :NEW.DATA_ATUALIZACAO := CURRENT_TIMESTAMP;
END;
/

-- Comentário do trigger
COMMENT ON TRIGGER TRG_MOTTU_USUARIOS_UPDATE IS 'Trigger para atualizar automaticamente a data de modificação dos usuários';
