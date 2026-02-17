package com.mypet.core.pessoa.adapter.out.persistence;

import com.mypet.core.pessoa.domain.Pessoa;
import com.mypet.core.pessoa.port.out.PessoaRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do adaptador de persistência.
 * Implementa a porta de saída e usa o Spring Data.
 */
@Component
public class PessoaRepositoryAdapter implements PessoaRepositoryPort {

    private final SpringDataPessoaRepository springDataRepository;

    public PessoaRepositoryAdapter(SpringDataPessoaRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return springDataRepository.save(pessoa);
    }

    @Override
    public List<Pessoa> findAll() {
        return springDataRepository.findAll();
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return springDataRepository.findById(id);
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {
        return springDataRepository.findByCpf(cpf);
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }
}
