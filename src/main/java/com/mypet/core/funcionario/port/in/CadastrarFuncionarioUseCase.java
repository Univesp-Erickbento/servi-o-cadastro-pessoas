package com.mypet.core.funcionario.port.in;

import com.mypet.core.funcionario.domain.Funcionario;

public interface CadastrarFuncionarioUseCase {

    Funcionario cadastrar(Funcionario funcionario, String authorizationHeader);
}