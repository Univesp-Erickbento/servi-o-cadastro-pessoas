package com.mypet.mypet.repositories;

import com.mypet.mypet.domain.model.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    // Método para buscar uma pessoa pelo nome de usuario
    Optional<LoginEntity> findByNomeUsuario(String nomeUsuario);
}
