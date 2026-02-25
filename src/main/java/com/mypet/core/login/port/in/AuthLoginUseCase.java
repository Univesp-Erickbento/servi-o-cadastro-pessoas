package com.mypet.core.login.port.in;

import com.mypet.core.login.adapter.in.dto.LoginRequest;
import com.mypet.core.login.adapter.in.dto.LoginResponse;

public interface AuthLoginUseCase {
    LoginResponse autenticar(LoginRequest request);
}