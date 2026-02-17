package com.mypet.core.pessoa.adapter.out.persistence;
// java
package com.mypet.core.pessoa.port.out;

import com.mypet.core.pessoa.domain.PessoaEntity;
import java.util.List;
import java.util.Optional;

/**
 * Porta de saída para persistência de Pessoa.
 * Define o contrato que o adaptador de persistência deve implementar.
 */
public interface PessoaRepositoryPort {
    PessoaEntity save(PessoaEntity pessoa);
    List<PessoaEntity> findAll();
    Optional<PessoaEntity> findById(Long id);
    Optional<PessoaEntity> findByCpf(String cpf);
    void deleteById(Long id);
}
