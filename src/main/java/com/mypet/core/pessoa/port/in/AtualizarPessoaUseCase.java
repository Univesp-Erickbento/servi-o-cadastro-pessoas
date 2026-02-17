package com.mypet.core.pessoa.port.in;

import com.mypet.core.pessoa.domain.Pessoa;

import java.util.Optional;

/**
 * Porta de entrada para atualizar pessoa.
 */
public interface AtualizarPessoaUseCase {
    Optional<Pessoa> executar(Long id, Pessoa pessoaAtualizada, String authorizationHeader);
}
