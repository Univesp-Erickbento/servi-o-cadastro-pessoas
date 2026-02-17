package com.mypet.core.pessoa.port.in;

import com.mypet.core.pessoa.domain.Pessoa;

/**
 * Porta de entrada (use case) para cadastrar pessoa.
 */
public interface CadastrarPessoaUseCase {
    Pessoa executar(Pessoa pessoa, String authorizationHeader);
}
