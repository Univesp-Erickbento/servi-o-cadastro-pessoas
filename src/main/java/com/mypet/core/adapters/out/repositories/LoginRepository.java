package com.mypet.core.adapters.out.repositories;

import com.mypet.core.application.core.domain.model.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    // Método para buscar uma pessoa pelo nome de usuario
    Optional<LoginEntity> findByNomeUsuario(String nomeUsuario);
}
