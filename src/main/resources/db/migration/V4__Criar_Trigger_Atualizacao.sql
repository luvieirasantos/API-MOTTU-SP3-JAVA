-- V4__Criar_Trigger_Atualizacao.sql
-- Criação de trigger para atualizar automaticamente a data de modificação

-- Trigger para updated_at (já criado em V1, mas mantido para compatibilidade)
CREATE OR REPLACE TRIGGER TRG_MOTTU_USUARIOS_BU
BEFORE UPDATE ON mottu_usuarios_sistema
FOR EACH ROW
BEGIN
  :NEW.data_atualizacao := SYSTIMESTAMP;
END;
/

-- Comentário do trigger
COMMENT ON TRIGGER TRG_MOTTU_USUARIOS_BU ON mottu_usuarios_sistema IS 'Trigger para atualizar automaticamente a data de modificação dos usuários';
