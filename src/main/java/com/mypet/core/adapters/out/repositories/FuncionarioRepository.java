package com.mypet.core.adapters.out.repositories;

import com.mypet.core.application.core.domain.model.FuncionariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionariosEntity, Long> {
    Optional<FuncionariosEntity> findByPessoaId(Long pessoaId);
}
