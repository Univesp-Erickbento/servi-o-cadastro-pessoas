package com.mypet.core.cliente.adapter.out.persistence;

import com.mypet.core.cliente.adapter.out.persistence.entity.ClienteJpaEntity;
import com.mypet.core.cliente.domain.Cliente;
import com.mypet.core.cliente.domain.ClienteId;
import com.mypet.core.pessoa.domain.PessoaId;

/**
 * Mapper para converter Cliente <-> ClienteJpaEntity
 * Corrigido para usar os nomes corretos de getters/setters da entity.
 */
public class ClientePersistenceMapper {

    /**
     * Converte ClienteJpaEntity -> Cliente (domínio)
     */
    public static Cliente toDomain(ClienteJpaEntity entity) {
        if (entity == null) return null;

        return new Cliente(
                entity.getId() != null ? ClienteId.of(entity.getId()) : ClienteId.empty(),
                entity.getPessoaId() != null ? PessoaId.of(entity.getPessoaId()) : null,
                entity.getClienteReg(),
                entity.getClienteStatus() // ✅ nome correto
        );
    }

    /**
     * Converte Cliente (domínio) -> ClienteJpaEntity
     */
    public static ClienteJpaEntity toEntity(Cliente cliente) {
        if (cliente == null) return null;

        ClienteJpaEntity entity = new ClienteJpaEntity();

        // Id
        entity.setId(
                cliente.getId() != null && cliente.getId().isPresent()
                        ? cliente.getId().asLong()
                        : null
        );

        // PessoaId
        entity.setPessoaId(
                cliente.getPessoaId() != null
                        ? cliente.getPessoaId().asLong()
                        : null
        );

        // Registro e Status
        entity.setClienteReg(cliente.getClienteReg());
        entity.setClienteStatus(cliente.getStatus()); // ✅ nome correto

        return entity;
    }
}
