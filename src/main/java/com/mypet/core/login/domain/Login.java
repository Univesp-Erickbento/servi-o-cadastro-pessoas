package com.mypet.core.login.domain;

import java.util.Objects;

public class Login {

        private LoginId id;
        private Long pessoaId;
        private String nomeUsuario;
        private String senha;
        private String perfis;

        private Login() {}

        public Login(LoginId id,
                     Long pessoaId,
                     String nomeUsuario,
                     String senha,
                     String perfis) {

                Objects.requireNonNull(pessoaId, "pessoaId é obrigatório");
                Objects.requireNonNull(nomeUsuario, "nomeUsuario é obrigatório");
                Objects.requireNonNull(senha, "senha é obrigatória");

                this.id = id;
                this.pessoaId = pessoaId;
                this.nomeUsuario = nomeUsuario;
                this.senha = senha;
                this.perfis = perfis;
        }

        public static Login novo(Long pessoaId,
                                 String nomeUsuario,
                                 String senha,
                                 String perfis) {

                return new Login(
                        LoginId.empty(),
                        pessoaId,
                        nomeUsuario,
                        senha,
                        perfis
                );
        }

        // =========================
        // REGRAS DE NEGÓCIO
        // =========================

        public void alterarSenha(String novaSenha) {
                Objects.requireNonNull(novaSenha, "nova senha é obrigatória");
                this.senha = novaSenha;
        }

        public void alterarPerfis(String novosPerfis) {
                this.perfis = novosPerfis;
        }

        // =========================
        // GETTERS
        // =========================

        public LoginId getId() { return id; }

        public void setId(LoginId id) { this.id = id; }

        public Long getPessoaId() { return pessoaId; }

        public String getNomeUsuario() { return nomeUsuario; }

        public String getSenha() { return senha; }

        public String getPerfis() { return perfis; }
}