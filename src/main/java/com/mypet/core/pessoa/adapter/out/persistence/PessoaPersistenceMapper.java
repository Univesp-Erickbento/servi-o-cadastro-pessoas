package com.mypet.core.pessoa.adapter.out.persistence;

import com.mypet.core.pessoa.adapter.out.persistence.entity.PessoaJpaEntity;
import com.mypet.core.pessoa.domain.Pessoa;
import com.mypet.core.pessoa.domain.PessoaId;

import java.util.LinkedHashSet;

public class PessoaPersistenceMapper {

    // ===============================
    // DOMAIN -> JPA
    // ===============================
    public PessoaJpaEntity toJpaEntity(Pessoa domain) {

        if (domain == null) {
            return null;
        }

        PessoaJpaEntity jpa = new PessoaJpaEntity();

        if (domain.getId() != null && domain.getId().isPresent()) {
            jpa.setId(domain.getId().asLong());
        }

        jpa.setNome(domain.getNome());
        jpa.setSobrenome(domain.getSobrenome());
        jpa.setCpf(domain.getCpf());
        jpa.setRg(domain.getRg());
        jpa.setGenero(domain.getGenero());
        jpa.setEmail(domain.getEmail());
        jpa.setContato(domain.getContato());
        jpa.setDataNascimento(domain.getDataNascimento());
        jpa.setDataCadastro(domain.getDataCadastro());

        if (domain.getPerfis() != null) {
            jpa.setPerfis(new LinkedHashSet<>(domain.getPerfis()));
        }

        return jpa;
    }

    // ===============================
    // JPA -> DOMAIN
    // ===============================
    public Pessoa toDomainEntity(PessoaJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        PessoaId pessoaId = null;

        if (jpa.getId() != null) {
            pessoaId = PessoaId.of(jpa.getId());
        }

        return new Pessoa(
                pessoaId,
                jpa.getNome(),
                jpa.getSobrenome(),
                jpa.getCpf(),
                jpa.getRg(),
                jpa.getGenero(),
                jpa.getPerfis(),
                jpa.getEmail(),
                jpa.getContato(),
                jpa.getDataNascimento(),
                jpa.getDataCadastro()
        );
    }
}
