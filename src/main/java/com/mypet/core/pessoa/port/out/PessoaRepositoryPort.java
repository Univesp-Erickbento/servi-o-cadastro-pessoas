package com.mypet.core.pessoa.port.out;

import com.mypet.core.pessoa.domain.Pessoa;

import java.util.List;
import java.util.Optional;

/**
 * Porta de saída para persistência de Pessoa.
 * Define o contrato que o adaptador de persistência deve implementar.
 */
public interface PessoaRepositoryPort {
    Pessoa save(Pessoa pessoa);
    List<Pessoa> findAll();
    Optional<Pessoa> findById(Long id);
    Optional<Pessoa> findByCpf(String cpf);
    void deleteById(Long id);
}
