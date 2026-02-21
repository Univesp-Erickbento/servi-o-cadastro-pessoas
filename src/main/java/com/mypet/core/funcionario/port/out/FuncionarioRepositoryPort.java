package com.mypet.core.funcionario.port.out;

import com.mypet.core.funcionario.domain.Funcionario;
import com.mypet.core.funcionario.domain.FuncionarioId;
import com.mypet.core.pessoa.domain.PessoaId;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepositoryPort {

    Funcionario save(Funcionario funcionario);

    Optional<Funcionario> findById(FuncionarioId id);

    List<Funcionario> findAll();

    void deleteById(FuncionarioId id);

    Optional<Funcionario> findByPessoaId(PessoaId pessoaId);
}