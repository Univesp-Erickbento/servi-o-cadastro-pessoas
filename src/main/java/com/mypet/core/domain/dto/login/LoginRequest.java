package com.mypet.core.domain.dto.login;

public record LoginRequest(
        String nomeUsuario,
        String senha) {
}