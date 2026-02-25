package com.mypet.core.login.port.in;

import com.mypet.core.login.domain.Login;
import com.mypet.core.pessoa.domain.Pessoa;

import java.util.List;

/**
 * Porta de entrada para listar pessoas.
 */
public interface ListarloginUseCase {
    List<Login> executar();
}
