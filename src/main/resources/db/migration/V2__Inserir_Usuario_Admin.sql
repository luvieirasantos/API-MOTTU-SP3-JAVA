-- V2__Inserir_Usuario_Admin.sql
-- Inserção do usuário administrador padrão
-- Senha: admin123 (criptografada com BCrypt)

DECLARE
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count FROM user_tables WHERE table_name = 'MOTTU_USUARIOS_SISTEMA';
  IF v_count > 0 THEN
    EXECUTE IMMEDIATE q'[
      MERGE INTO mottu_usuarios_sistema tgt
      USING (SELECT 'admin@mottu.com' AS email FROM dual) src
      ON (tgt.email_usuario = src.email)
      WHEN NOT MATCHED THEN
        INSERT (nome_completo, email_usuario, senha_criptografada, perfil_acesso, ativo)
        VALUES ('Administrador Sistema','admin@mottu.com','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa','ADMIN',1)
    ]';
  END IF;
END;
/
