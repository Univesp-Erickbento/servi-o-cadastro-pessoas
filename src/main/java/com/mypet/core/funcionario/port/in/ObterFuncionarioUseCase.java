package com.mypet.core.funcionario.port.in;

import com.mypet.core.funcionario.domain.Funcionario;

import java.util.Optional;

public interface ObterFuncionarioUseCase {

    Optional<Funcionario> obterPorId(Long id, String authorizationHeader);
}