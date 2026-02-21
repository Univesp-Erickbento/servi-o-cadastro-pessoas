package com.mypet.core.funcionario.adapter.out.persistence;

import com.mypet.core.funcionario.adapter.out.persistence.entity.FuncionarioJpaEntity;
import com.mypet.core.funcionario.domain.Funcionario;
import com.mypet.core.funcionario.domain.FuncionarioId;
import com.mypet.core.funcionario.port.out.FuncionarioRepositoryPort;
import com.mypet.core.pessoa.domain.PessoaId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FuncionarioRepositoryAdapter implements FuncionarioRepositoryPort {

    private final SpringDataFuncionarioRepository repository;

    public FuncionarioRepositoryAdapter(SpringDataFuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {

        FuncionarioJpaEntity entity =
                FuncionarioPersistenceMapper.toEntity(funcionario);

        FuncionarioJpaEntity saved =
                repository.save(entity);

        return FuncionarioPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Funcionario> findById(FuncionarioId id) {

        if (id == null || !id.isPresent()) {
            return Optional.empty();
        }

        return repository.findById(id.asLong())
                .map(FuncionarioPersistenceMapper::toDomain);
    }

    @Override
    public List<Funcionario> findAll() {

        return repository.findAll()
                .stream()
                .map(FuncionarioPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(FuncionarioId id) {

        if (id != null && id.isPresent()) {
            repository.deleteById(id.asLong());
        }
    }

    @Override
    public Optional<Funcionario> findByPessoaId(PessoaId pessoaId) {

        if (pessoaId == null || !pessoaId.isPresent()) {
            return Optional.empty();
        }

        return repository.findByPessoaId(pessoaId.asLong())
                .map(FuncionarioPersistenceMapper::toDomain);
    }
}