package com.mypet.core.pessoa.adapter.out.persistence;

import com.mypet.core.pessoa.adapter.out.persistence.entity.PessoaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataPessoaRepository extends JpaRepository<PessoaJpaEntity, Long> {

    // Método para buscar uma pessoa pelo CPF
    Optional<PessoaJpaEntity> findByCpf(String cpf);
}