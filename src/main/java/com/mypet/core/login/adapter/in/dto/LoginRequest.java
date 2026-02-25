package com.mypet.core.login.adapter.in.dto;

public record LoginRequest(
        Long pessoaId,
        String nomeUsuario,
        String senha
) {}