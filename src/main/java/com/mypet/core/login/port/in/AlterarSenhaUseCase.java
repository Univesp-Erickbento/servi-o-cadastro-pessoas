package com.mypet.core.login.port.in;

import com.mypet.core.login.adapter.in.dto.LoginRequest;

public interface AlterarSenhaUseCase {

    String alterarSenha(String nomeUsuario, LoginRequest request);
}