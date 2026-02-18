package com.mypet.core.cliente.port.in;


import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.pessoa.domain.PessoaId;

import java.util.List;
import java.util.Optional;

// Para listar todos os clientes
public interface ListarClientesUseCase {
    List<Cliente> executar(String authorizationHeader);
}