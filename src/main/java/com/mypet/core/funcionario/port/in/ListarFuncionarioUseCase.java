package com.mypet.core.funcionario.port.in;

import com.mypet.core.funcionario.domain.Funcionario;

import java.util.List;

public interface ListarFuncionarioUseCase {

    List<Funcionario> listar(String authorizationHeader);
}