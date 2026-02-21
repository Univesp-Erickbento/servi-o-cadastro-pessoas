package com.mypet.core.funcionario.port.in;

import com.mypet.core.funcionario.domain.Funcionario;
import com.mypet.core.pessoa.domain.PessoaId;

import java.util.Optional;

public interface ObterFuncionarioPorPessoaUseCase {

    Optional<Funcionario> obterPorPessoaId(PessoaId pessoaId, String authorizationHeader);
}