package com.mypet.core.cliente.port.out;

import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.cliente.domain.ClienteId;
import com.mypet.core.pessoa.domain.PessoaId;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(ClienteId id);

    List<Cliente> findAll();

    void deleteById(ClienteId id);

    Optional<Cliente> findByPessoaId(PessoaId pessoaId);
}
