package com.mypet.core.domain.dto.clientedto;


public record ClienteEnvioDTO(
        long pessoaId,
        String clienteReg,
        String clienteStatus
) {}
