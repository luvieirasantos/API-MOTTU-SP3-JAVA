package com.fiap.mottu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.mottu.entity.MottuUsuario;

@Repository
public interface MottuUsuarioRepository extends JpaRepository<MottuUsuario, Long> {
    
    Optional<MottuUsuario> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    Optional<MottuUsuario> findByEmailAndAtivoTrue(String email);
}
