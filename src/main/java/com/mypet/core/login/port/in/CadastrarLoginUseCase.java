package com.mypet.core.login.port.in;

import com.mypet.core.login.adapter.in.dto.LoginRegisterRequest;

public interface CadastrarLoginUseCase {
    String cadastrar(LoginRegisterRequest request);
}