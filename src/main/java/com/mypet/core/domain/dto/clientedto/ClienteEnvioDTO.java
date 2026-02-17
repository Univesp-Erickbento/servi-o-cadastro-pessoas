package com.mypet.mypet.domain.dto.clientedto;


public record ClienteEnvioDTO(
        long pessoaId,
        String clienteReg,
        String clienteStatus
) {}
