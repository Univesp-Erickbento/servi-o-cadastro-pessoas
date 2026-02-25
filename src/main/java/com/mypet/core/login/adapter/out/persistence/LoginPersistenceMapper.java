package com.mypet.core.login.adapter.out.persistence;

import com.mypet.core.login.adapter.out.persistence.entity.LoginJpaEntity;
import com.mypet.core.login.domain.Login;
import com.mypet.core.login.domain.LoginId;
import org.springframework.stereotype.Component;

@Component
public class LoginPersistenceMapper {

    // =========================
    // DOMAIN -> JPA
    // =========================
    public LoginJpaEntity toJpaEntity(Login domain) {

        if (domain == null) {
            return null;
        }

        LoginJpaEntity entity = new LoginJpaEntity();

        // ID
        if (domain.getId() != null && domain.getId().isPresent()) {
            entity.setId(domain.getId().asLong());
        }

        // pessoaId agora é Long (não PessoaId)
        entity.setPessoaId(domain.getPessoaId());

        entity.setNomeUsuario(domain.getNomeUsuario());
        entity.setSenha(domain.getSenha());
        entity.setPerfis(domain.getPerfis());

        return entity;
    }

    // =========================
    // JPA -> DOMAIN
    // =========================
    public Login toDomain(LoginJpaEntity entity) {

        if (entity == null) {
            return null;
        }

        LoginId loginId = entity.getId() != null
                ? LoginId.of(entity.getId())
                : LoginId.empty();

        return new Login(
                loginId,
                entity.getPessoaId(), // agora é Long direto
                entity.getNomeUsuario(),
                entity.getSenha(),
                entity.getPerfis()
        );
    }
}