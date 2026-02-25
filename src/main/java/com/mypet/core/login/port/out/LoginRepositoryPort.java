package com.mypet.core.login.port.out;

import com.mypet.core.login.domain.Login;
import com.mypet.core.login.domain.LoginId;

import java.util.Optional;

/**
 * Porta de saída para persistência de Login.
 * Define o contrato que o adaptador deve implementar.
 */
public interface LoginRepositoryPort {

    Login save(Login login);

    Optional<Login> findById(LoginId id);

    Optional<Login> findByNomeUsuario(String nomeUsuario);

    void deleteById(LoginId id);
}