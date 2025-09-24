-- V4__Criar_Trigger_Atualizacao.sql
-- Criação de trigger para atualizar automaticamente a data de modificação

-- Trigger para updated_at (já criado em V1). Recria somente se não existir
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

-- Comentário removido: Oracle não suporta COMMENT ON TRIGGER
