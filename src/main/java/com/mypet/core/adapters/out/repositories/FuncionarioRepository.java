package com.mypet.core.adapters.out.repositories;

import com.mypet.core.funcionario.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByPessoaId(Long pessoaId);
}
