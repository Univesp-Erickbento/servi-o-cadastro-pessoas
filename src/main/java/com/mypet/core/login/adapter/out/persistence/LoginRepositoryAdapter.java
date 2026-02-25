package com.mypet.core.login.adapter.out.persistence;

import com.mypet.core.login.adapter.out.persistence.entity.LoginJpaEntity;
import com.mypet.core.login.domain.Login;
import com.mypet.core.login.domain.LoginId;
import com.mypet.core.login.port.out.LoginRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginRepositoryAdapter implements LoginRepositoryPort {

    private final SpringDataLoginRepository repository;
    private final LoginPersistenceMapper mapper;

    public LoginRepositoryAdapter(SpringDataLoginRepository repository,
                                  LoginPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Login save(Login login) {

        LoginJpaEntity entity = mapper.toJpaEntity(login);
        LoginJpaEntity saved = repository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Login> findById(LoginId id) {

        if (id == null || !id.isPresent()) {
            return Optional.empty();
        }

        return repository.findById(id.asLong())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Login> findByNomeUsuario(String nomeUsuario) {

        return repository.findByNomeUsuario(nomeUsuario)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(LoginId id) {

        if (id != null && id.isPresent()) {
            repository.deleteById(id.asLong());
        }
    }
}