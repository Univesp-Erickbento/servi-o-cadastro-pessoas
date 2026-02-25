package com.mypet.core.login.adapter.out.persistence;

import com.mypet.core.login.adapter.out.persistence.entity.LoginJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataLoginRepository
        extends JpaRepository<LoginJpaEntity, Long> {

    Optional<LoginJpaEntity> findByNomeUsuario(String nomeUsuario);
}