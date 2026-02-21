package com.mypet.core.funcionario.port.in;

public interface RemoverFuncionarioUseCase {

    void remover(Long id, String authorizationHeader);
}