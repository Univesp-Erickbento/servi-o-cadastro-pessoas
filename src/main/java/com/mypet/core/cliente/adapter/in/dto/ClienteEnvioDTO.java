package com.mypet.core.cliente.adapter.in.dto;


public record ClienteEnvioDTO(
        long pessoaId,
        String clienteReg,
        String clienteStatus
) {}
