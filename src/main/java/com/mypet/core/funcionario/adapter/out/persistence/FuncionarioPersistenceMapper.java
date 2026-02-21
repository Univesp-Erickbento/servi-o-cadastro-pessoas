package com.mypet.core.funcionario.adapter.out.persistence;

import com.mypet.core.funcionario.adapter.out.persistence.entity.FuncionarioJpaEntity;
import com.mypet.core.funcionario.domain.Funcionario;
import com.mypet.core.funcionario.domain.FuncionarioId;
import com.mypet.core.pessoa.domain.PessoaId;

public class FuncionarioPersistenceMapper {

    public static Funcionario toDomain(FuncionarioJpaEntity entity) {
        if (entity == null) return null;

        return new Funcionario(
                entity.getId() != null ? FuncionarioId.of(entity.getId()) : FuncionarioId.empty(),
                entity.getPessoaId() != null ? PessoaId.of(entity.getPessoaId()) : null,
                entity.getFuncionarioTipo(),
                entity.getFuncionarioReg(),
                entity.getFuncionarioStatus(),
                entity.getDataDeAdmissao(),
                entity.getDataDeDemissao()
        );
    }

    public static FuncionarioJpaEntity toEntity(Funcionario funcionario) {
        if (funcionario == null) return null;

        FuncionarioJpaEntity entity = new FuncionarioJpaEntity();

        entity.setId(
                funcionario.getId() != null && funcionario.getId().isPresent()
                        ? funcionario.getId().asLong()
                        : null
        );

        entity.setPessoaId(
                funcionario.getPessoaId() != null
                        ? funcionario.getPessoaId().asLong()
                        : null
        );

        entity.setFuncionarioTipo(funcionario.getFuncionarioTipo());
        entity.setFuncionarioReg(funcionario.getFuncionarioReg());
        entity.setFuncionarioStatus(funcionario.getFuncionarioStatus());
        entity.setDataDeAdmissao(funcionario.getDataDeAdmissao());
        entity.setDataDeDemissao(funcionario.getDataDeDemissao());

        return entity;
    }
}