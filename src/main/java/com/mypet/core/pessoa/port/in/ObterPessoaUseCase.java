package com.mypet.core.pessoa.port.in;

import com.mypet.core.pessoa.domain.Pessoa;

import java.util.Optional;

/**
 * Porta de entrada para obter pessoa por ID ou CPF.
 */
public interface ObterPessoaUseCase {
    Optional<Pessoa> obterPorId(Long id, String authorizationHeader);
    Optional<Pessoa> obterPorCpf(String cpf, String authorizationHeader);
}
