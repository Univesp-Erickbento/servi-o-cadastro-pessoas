package com.mypet.core.login.application.usecase;

import com.mypet.core.login.adapter.in.dto.LoginRegisterRequest;
import com.mypet.core.login.adapter.in.dto.LoginRequest;
import com.mypet.core.login.adapter.in.dto.LoginResponse;
import com.mypet.core.login.domain.Login;
import com.mypet.core.login.port.in.AlterarSenhaUseCase;
import com.mypet.core.login.port.in.AuthLoginUseCase;
import com.mypet.core.login.port.in.CadastrarLoginUseCase;
import com.mypet.core.login.port.in.DeletarLoginUseCase;
import com.mypet.core.login.port.out.LoginRepositoryPort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoginService implements
        CadastrarLoginUseCase,
        AuthLoginUseCase,
        AlterarSenhaUseCase,
        DeletarLoginUseCase {

    private final JwtEncoder jwtEncoder;
    private final LoginRepositoryPort loginRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(JwtEncoder jwtEncoder,
                        LoginRepositoryPort loginRepository,
                        BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // CADASTRAR
    // =========================
    @Override
    public String cadastrar(LoginRegisterRequest request) {

        validarCamposCadastro(request);

        if (loginRepository.findByNomeUsuario(request.nomeUsuario()).isPresent()) {
            throw new RuntimeException("Usuário já existe!");
        }

        Login novoLogin = Login.novo(
                request.pessoaId(),
                request.nomeUsuario(),
                passwordEncoder.encode(request.senha()),
                "ROLE_USER"
        );

        loginRepository.save(novoLogin);

        return "Usuário registrado com sucesso!";
    }

    // =========================
    // AUTENTICAR
    // =========================
    @Override
    public LoginResponse autenticar(LoginRequest request) {

        validarCamposLogin(request);

        Login login = loginRepository
                .findByNomeUsuario(request.nomeUsuario())
                .orElseThrow(() ->
                        new BadCredentialsException("Usuário ou senha inválidos!"));

        if (!passwordEncoder.matches(request.senha(), login.getSenha())) {
            throw new BadCredentialsException("Usuário ou senha inválidos!");
        }

        return gerarJwt(login);
    }

    // =========================
    // ALTERAR SENHA
    // =========================
    @Override
    public String alterarSenha(String nomeUsuario, LoginRequest request) {

        if (request.senha() == null || request.senha().isBlank()) {
            throw new IllegalArgumentException("Nova senha é obrigatória!");
        }

        Login login = loginRepository
                .findByNomeUsuario(nomeUsuario)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado!"));

        login.alterarSenha(passwordEncoder.encode(request.senha()));

        loginRepository.save(login);

        return "Senha atualizada com sucesso!";
    }

    // =========================
    // DELETAR
    // =========================
    @Override
    public String deletar(String nomeUsuario) {

        Login login = loginRepository
                .findByNomeUsuario(nomeUsuario)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado!"));

        loginRepository.deleteById(login.getId());

        return "Usuário excluído com sucesso!";
    }

    // =========================
    // JWT (AJUSTADO)
    // =========================
    private LoginResponse gerarJwt(Login login) {

        Instant now = Instant.now();
        long expiresIn = 300L; // 5 minutos

        // 🔥 TRATAMENTO DE NULL AQUI
        String roles = (login.getPerfis() != null && !login.getPerfis().isBlank())
                ? login.getPerfis()
                : "ROLE_USER";

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("servico-cadastro-mypet")
                .subject(login.getNomeUsuario())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("roles", roles)
                .build();

        String token = jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();

        return new LoginResponse(token, expiresIn);
    }

    // =========================
    // VALIDAÇÕES
    // =========================
    private void validarCamposCadastro(LoginRegisterRequest request) {

        if (request.pessoaId() == null) {
            throw new IllegalArgumentException("pessoaId é obrigatório!");
        }

        if (request.nomeUsuario() == null || request.nomeUsuario().isBlank()
                || request.senha() == null || request.senha().isBlank()) {
            throw new IllegalArgumentException("Nome de usuário e senha são obrigatórios!");
        }
    }

    private void validarCamposLogin(LoginRequest request) {

        if (request.nomeUsuario() == null || request.nomeUsuario().isBlank()
                || request.senha() == null || request.senha().isBlank()) {
            throw new IllegalArgumentException("Nome de usuário e senha são obrigatórios!");
        }
    }
}