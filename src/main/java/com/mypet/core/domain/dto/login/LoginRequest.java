package com.mypet.mypet.domain.dto.login;

public record LoginRequest(
        String nomeUsuario,
        String senha) {
}