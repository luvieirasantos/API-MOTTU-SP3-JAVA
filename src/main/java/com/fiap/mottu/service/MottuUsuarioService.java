package com.fiap.mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.mottu.dto.CadastroRequest;
import com.fiap.mottu.entity.MottuUsuario;
import com.fiap.mottu.entity.PerfilUsuario;
import com.fiap.mottu.repository.MottuUsuarioRepository;

/**
 * SERVIÇO: MottuUsuarioService
 * 
 * Este serviço é responsável por toda a lógica de negócio relacionada
 * aos usuários do sistema Mottu. Implementa UserDetailsService para
 * integração com Spring Security e gerencia operações CRUD de usuários.
 * 
 * FUNÇÃO: Lógica de negócio para usuários
 * SEGURANÇA: Integração com Spring Security
 * TRANSAÇÕES: Gerenciamento de transações de banco
 * AUTENTICAÇÃO: Carregamento de usuários para login
 */
@Service
@Transactional
public class MottuUsuarioService implements UserDetailsService {

    /**
     * REPOSITÓRIO DE USUÁRIOS
     * 
     * FUNÇÃO: Acesso aos dados de usuários no banco
     * INJEÇÃO: @Autowired para injeção de dependência
     * OPERAÇÕES: CRUD, busca por email, validações
     */
    @Autowired
    private MottuUsuarioRepository usuarioRepository;

    /**
     * ENCODER DE SENHAS
     * 
     * FUNÇÃO: Criptografia e verificação de senhas
     * INJEÇÃO: @Autowired para injeção de dependência
     * ALGORITMO: BCrypt (configurado no Spring Security)
     * 
     * IMPORTANTE: 
     * - Nunca armazena senhas em texto puro
     * - Gera hash único para cada senha
     * - Permite verificação segura de senhas
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * MÉTODO: loadUserByUsername(String email)
     * 
     * IMPLEMENTAÇÃO: UserDetailsService (Spring Security)
     * FUNÇÃO: Carrega usuário para autenticação
     * RETORNO: UserDetails (MottuUsuario implementa esta interface)
     * USO: Spring Security chama este método durante login
     * 
     * IMPORTANTE: 
     * - Método obrigatório para Spring Security
     * - Busca apenas usuários ativos (ativo = true)
     * - Lança exceção se usuário não for encontrado
     * 
     * FLUXO: 
     * 1. Usuário tenta fazer login
     * 2. Spring Security chama este método
     * 3. Busca usuário no banco por email
     * 4. Retorna usuário se encontrado e ativo
     * 5. Spring Security valida senha
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmailAndAtivoTrue(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }

    /**
     * MÉTODO: cadastrarUsuario(CadastroRequest request)
     * 
     * FUNÇÃO: Cria novo usuário no sistema
     * RETORNO: MottuUsuario criado e salvo
     * USO: Formulário de cadastro de novos usuários
     * 
     * VALIDAÇÕES:
     * - Email não pode estar duplicado
     * - Todos os campos obrigatórios preenchidos
     * 
     * CONFIGURAÇÕES AUTOMÁTICAS:
     * - Perfil padrão: USUARIO (não admin)
     * - Status: ativo = true
     * - Senha: criptografada com BCrypt
     * 
     * IMPORTANTE: 
     * - Verifica duplicação de email antes de salvar
     * - Criptografa senha antes de armazenar
     * - Define perfil padrão para novos usuários
     * 
     * FLUXO:
     * 1. Verifica se email já existe
     * 2. Cria nova instância de MottuUsuario
     * 3. Define dados do request
     * 4. Criptografa senha
     * 5. Define perfil e status padrão
     * 6. Salva no banco
     */
    public MottuUsuario cadastrarUsuario(CadastroRequest request) {
        // VALIDAÇÃO: Verifica se email já está cadastrado
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + request.getEmail());
        }

        // CRIAÇÃO: Nova instância de usuário
        MottuUsuario usuario = new MottuUsuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        
        // SEGURANÇA: Criptografa senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        
        // CONFIGURAÇÃO: Define perfil e status padrão
        usuario.setPerfil(PerfilUsuario.USUARIO);
        usuario.setAtivo(true);

        // PERSISTÊNCIA: Salva usuário no banco
        return usuarioRepository.save(usuario);
    }

    /**
     * MÉTODO: buscarPorEmail(String email)
     * 
     * FUNÇÃO: Busca usuário pelo email
     * RETORNO: MottuUsuario encontrado
     * USO: Busca de usuários para operações específicas
     * 
     * IMPORTANTE: 
     * - Busca qualquer usuário (ativo ou inativo)
     * - Lança exceção se não encontrar
     * - Diferente de loadUserByUsername (que só busca ativos)
     * 
     * USOS:
     * - Administração (ver todos os usuários)
     * - Operações de manutenção
     * - Validações específicas
     */
    public MottuUsuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }

    /**
     * MÉTODO: verificarSenha(String senhaDigitada, String senhaCriptografada)
     * 
     * FUNÇÃO: Verifica se senha digitada corresponde à senha armazenada
     * RETORNO: boolean (true se senhas coincidem, false caso contrário)
     * USO: Validação de senha durante login
     * 
     * IMPORTANTE: 
     * - Compara senha em texto com hash criptografado
     * - Usa BCrypt para verificação segura
     * - Nunca descriptografa senha (impossível com BCrypt)
     * 
     * SEGURANÇA:
     * - BCrypt gera hash único para cada senha
     * - Mesmo texto pode gerar hashes diferentes
     * - Método matches() faz comparação segura
     * 
     * USO: 
     * - Login de usuários
     * - Alteração de senha
     * - Validações de segurança
     */
    public boolean verificarSenha(String senhaDigitada, String senhaCriptografada) {
        return passwordEncoder.matches(senhaDigitada, senhaCriptografada);
    }
}
