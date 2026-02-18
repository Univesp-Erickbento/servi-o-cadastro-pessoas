package com.mypet.core.cliente.port.in;

import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.pessoa.domain.PessoaId;

import java.util.List;
import java.util.Optional;


public interface RemoverClienteUseCase {

    void executar(Long id, String authorizationHeader);

}
