package com.fiap.mottu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mottu.entity.MottuUsuario;

/**
 * REPOSITORY: MottuUsuarioRepository
 * 
 * Este repositório é responsável por todas as operações de banco de dados
 * relacionadas à entidade MottuUsuario. Estende JpaRepository para herdar
 * métodos CRUD básicos e adiciona métodos customizados específicos.
 * 
 * FUNÇÃO: Camada de acesso a dados para usuários
 * TECNOLOGIA: Spring Data JPA com Oracle
 * OPERAÇÕES: CRUD, busca por email, verificação de existência
 */
@Repository
public interface MottuUsuarioRepository extends JpaRepository<MottuUsuario, Long> {
    
    /**
     * MÉTODO: findByEmail(String email)
     * 
     * FUNÇÃO: Busca um usuário pelo email
     * RETORNO: Optional<MottuUsuario> (pode estar vazio se não encontrar)
     * USO: Login, verificação de usuário existente
     * 
     * IMPORTANTE: 
     * - Usado pelo Spring Security para autenticação
     * - Email é único no sistema (constraint unique)
     * - Optional evita NullPointerException
     * 
     * EXEMPLO USO: repository.findByEmail("usuario@email.com")
     */
    Optional<MottuUsuario> findByEmail(String email);
    
    /**
     * MÉTODO: existsByEmail(String email)
     * 
     * FUNÇÃO: Verifica se existe um usuário com o email informado
     * RETORNO: boolean (true se existe, false se não existe)
     * USO: Validação de cadastro, verificação de duplicação
     * 
     * IMPORTANTE:
     * - Mais eficiente que buscar o usuário completo
     * - Usado para validar se email já está em uso
     * - Retorna apenas true/false, não dados do usuário
     * 
     * EXEMPLO USO: repository.existsByEmail("usuario@email.com")
     */
    boolean existsByEmail(String email);
    
    /**
     * MÉTODO: findByEmailAndAtivoTrue(String email)
     * 
     * FUNÇÃO: Busca usuário ativo pelo email
     * RETORNO: Optional<MottuUsuario> (apenas usuários ativos)
     * USO: Login (não permite login de contas desativadas)
     * 
     * IMPORTANTE:
     * - Spring Security usa para autenticação
     * - Garante que apenas usuários ativos possam fazer login
     * - Combina busca por email + filtro de status ativo
     * 
     * EXEMPLO USO: repository.findByEmailAndAtivoTrue("usuario@email.com")
     * 
     * NOTA: Este método é mais seguro que findByEmail() para login,
     * pois garante que a conta não foi desativada
     */
    Optional<MottuUsuario> findByEmailAndAtivoTrue(String email);
}
