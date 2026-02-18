package com.mypet.core.cliente.adapter.out.persistence;

import com.mypet.core.cliente.adapter.out.persistence.entity.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteJpaEntity, Long> {

    // você pode criar métodos extras se precisar, ex:
     Optional<ClienteJpaEntity> findByPessoaId(Long pessoaId);

}
