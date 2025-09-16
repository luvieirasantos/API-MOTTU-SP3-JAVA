-- V4__Criar_Trigger_Atualizacao.sql
-- Criação de função e trigger para atualizar automaticamente a data de modificação

CREATE OR REPLACE FUNCTION update_data_atualizacao()
RETURNS TRIGGER AS $$
BEGIN
    NEW.data_atualizacao = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_mottu_usuarios_update
    BEFORE UPDATE ON mottu_usuarios_sistema
    FOR EACH ROW
    EXECUTE FUNCTION update_data_atualizacao();

-- Comentário do trigger
COMMENT ON TRIGGER trg_mottu_usuarios_update ON mottu_usuarios_sistema IS 'Trigger para atualizar automaticamente a data de modificação dos usuários';
