-- V3__Inserir_Usuario_Teste.sql
-- Inserção de usuário de teste para demonstração
-- Senha: user123 (criptografada com BCrypt)

DECLARE
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count FROM user_tables WHERE table_name = 'MOTTU_USUARIOS_SISTEMA';
  IF v_count > 0 THEN
    EXECUTE IMMEDIATE q'[
      MERGE INTO mottu_usuarios_sistema tgt
      USING (SELECT 'user@mottu.com' AS email FROM dual) src
      ON (tgt.email_usuario = src.email)
      WHEN NOT MATCHED THEN
        INSERT (nome_completo, email_usuario, senha_criptografada, perfil_acesso, ativo)
        VALUES ('Usuário Teste','user@mottu.com','$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a','USUARIO',1)
    ]';
  END IF;
END;
/
