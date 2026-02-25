package com.mypet.core.login.adapter.in.dto;

public record LoginRegisterRequest(Long pessoaId,
                                   String nomeUsuario,
                                   String senha
) {}
