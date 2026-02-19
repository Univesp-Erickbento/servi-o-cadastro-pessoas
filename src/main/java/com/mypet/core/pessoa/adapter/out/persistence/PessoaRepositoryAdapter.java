package com.mypet.core.pessoa.adapter.out.persistence;

import com.mypet.core.pessoa.adapter.out.persistence.entity.PessoaJpaEntity;
import com.mypet.core.pessoa.domain.Pessoa;
import com.mypet.core.pessoa.port.out.PessoaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementação do adaptador de persistência.
 * Implementa a porta de saída e usa o Spring Data.
 */
@Component
public class PessoaRepositoryAdapter implements PessoaRepositoryPort {

    private final SpringDataPessoaRepository springDataRepository;
    private final PessoaPersistenceMapper mapper;

    // Injeção via construtor do Spring
    public PessoaRepositoryAdapter(SpringDataPessoaRepository springDataRepository,
                                   PessoaPersistenceMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        // Converte Domain -> JPA
        PessoaJpaEntity entity = mapper.toJpaEntity(pessoa);
        // Salva no banco
        PessoaJpaEntity saved = springDataRepository.save(entity);
        // Converte JPA -> Domain
        return mapper.toDomainEntity(saved);
    }

    @Override
    public List<Pessoa> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomainEntity);
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {
        return springDataRepository.findByCpf(cpf)
                .map(mapper::toDomainEntity);
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }
}
