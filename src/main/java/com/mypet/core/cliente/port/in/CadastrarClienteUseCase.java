package com.mypet.core.cliente.port.in;

import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.pessoa.domain.PessoaId;

public interface CadastrarClienteUseCase {

    Cliente executar(Cliente cliente, String authorizationHeader);

}
