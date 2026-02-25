package com.mypet.core.login.port.in;

import com.mypet.core.login.domain.Login;
import com.mypet.core.login.domain.LoginId;

import java.util.Optional;

public interface BuscarLoginUseCase {

    Optional<Login> porId(LoginId id);

    Optional<Login> porNomeUsuario(String nomeUsuario);
}