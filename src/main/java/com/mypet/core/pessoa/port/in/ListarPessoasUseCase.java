package com.mypet.core.pessoa.port.in;

import com.mypet.core.pessoa.domain.Pessoa;

import java.util.List;

/**
 * Porta de entrada para listar pessoas.
 */
public interface ListarPessoasUseCase {
    List<Pessoa> executar();
}
