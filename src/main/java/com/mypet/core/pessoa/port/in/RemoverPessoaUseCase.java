package com.mypet.core.pessoa.port.in;

/**
 * Porta de entrada para remover pessoa.
 */
public interface RemoverPessoaUseCase {
    void executar(Long id, String authorizationHeader);
}
