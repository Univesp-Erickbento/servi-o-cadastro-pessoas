package com.mypet.core.funcionario.adapter.out.persistence;

import com.mypet.core.funcionario.adapter.out.persistence.entity.FuncionarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface SpringDataFuncionarioRepository
        extends JpaRepository<FuncionarioJpaEntity, Long> {

    Optional<FuncionarioJpaEntity> findByPessoaId(Long pessoaId);
}