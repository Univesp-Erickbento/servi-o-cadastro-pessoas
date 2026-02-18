package com.mypet.core.cliente.adapter.out.persistence;

import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.cliente.domain.ClienteId;
import com.mypet.core.pessoa.domain.PessoaId;
import com.mypet.core.cliente.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final SpringDataClienteRepository springDataRepository;

    public ClienteRepositoryAdapter(SpringDataClienteRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        // converte para entity
        var entity = ClientePersistenceMapper.toEntity(cliente);

        // salva no banco
        var saved = springDataRepository.save(entity);

        // converte de volta para domínio
        return ClientePersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Cliente> findById(ClienteId id) {
        if (id == null || !id.isPresent()) {
            return Optional.empty();
        }

        return springDataRepository.findById(id.asLong())
                .map(ClientePersistenceMapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(ClientePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(ClienteId id) {
        if (id != null && id.isPresent()) {
            springDataRepository.deleteById(id.asLong());
        }
    }

    @Override
    public Optional<Cliente> findByPessoaId(PessoaId pessoaId) {
        if (pessoaId == null || !pessoaId.isPresent()) {
            return Optional.empty();
        }

        return springDataRepository.findByPessoaId(pessoaId.asLong())
                .map(ClientePersistenceMapper::toDomain);
    }
}
