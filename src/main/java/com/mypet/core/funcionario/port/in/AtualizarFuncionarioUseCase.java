package com.mypet.core.funcionario.port.in;

import com.mypet.core.funcionario.domain.Funcionario;

import java.util.Optional;

public interface AtualizarFuncionarioUseCase {

    Optional<Funcionario> atualizar(Long id, Funcionario funcionarioAtualizado, String authorizationHeader);
}