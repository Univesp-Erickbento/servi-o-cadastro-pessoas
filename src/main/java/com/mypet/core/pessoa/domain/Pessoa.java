package com.mypet.core.pessoa.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade de domínio Login (sem anotações JPA).
 */
public class Pessoa {

        private PessoaId id;
        private String nome;
        private String sobrenome;
        private String cpf;
        private String rg;
        private String genero;
        private String perfil; // ✅ agora é String
        private String email;
        private String contato;
        private LocalDate dataNascimento;
        private LocalDate dataCadastro;

        private Pessoa() {
                // construtor para uso interno / mapeadores
        }

        public Pessoa(PessoaId id,
                      String nome,
                      String sobrenome,
                      String cpf,
                      String rg,
                      String genero,
                      String perfil,
                      String email,
                      String contato,
                      LocalDate dataNascimento,
                      LocalDate dataCadastro) {

                Objects.requireNonNull(nome, "nome é obrigatório");
                Objects.requireNonNull(cpf, "cpf é obrigatório");

                this.id = id;
                this.nome = nome;
                this.sobrenome = sobrenome;
                this.cpf = cpf;
                this.rg = rg;
                this.genero = genero;
                this.perfil = perfil;
                this.email = email;
                this.contato = contato;
                this.dataNascimento = dataNascimento;
                this.dataCadastro = dataCadastro != null ? dataCadastro : LocalDate.now();
        }

        public static Pessoa of(PessoaId id,
                                String nome,
                                String sobrenome,
                                String cpf,
                                LocalDate dataNascimento) {

                return new Pessoa(
                        id,
                        nome,
                        sobrenome,
                        cpf,
                        null,
                        null,
                        null,
                        null,
                        null,
                        dataNascimento,
                        LocalDate.now()
                );
        }

        // =========================
        // GETTERS
        // =========================

        public PessoaId getId() {
                return id;
        }

        public void setId(PessoaId id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public String getSobrenome() {
                return sobrenome;
        }

        public String getCpf() {
                return cpf;
        }

        public String getRg() {
                return rg;
        }

        public String getGenero() {
                return genero;
        }

        public String getPerfil() {   // ✅ agora é String
                return perfil;
        }

        public String getEmail() {
                return email;
        }

        public String getContato() {
                return contato;
        }

        public LocalDate getDataNascimento() {
                return dataNascimento;
        }

        public LocalDate getDataCadastro() {
                return dataCadastro;
        }

        // =========================
        // MÉTODO DE DOMÍNIO PARA ATUALIZAÇÃO COMPLETA
        // =========================

        public void atualizarDados(
                String nome,
                String sobrenome,
                String cpf,
                String rg,
                String genero,
                String perfil,
                String email,
                String contato,
                LocalDate dataNascimento
        ) {

                Objects.requireNonNull(nome, "nome é obrigatório");
                Objects.requireNonNull(cpf, "cpf é obrigatório");

                this.nome = nome;
                this.sobrenome = sobrenome;
                this.cpf = cpf;
                this.rg = rg;
                this.genero = genero;
                this.perfil = perfil;
                this.email = email;
                this.contato = contato;
                this.dataNascimento = dataNascimento;
        }

        public void atualizarContato(String novoContato) {
                this.contato = novoContato;
        }

        public void atualizarEmail(String novoEmail) {
                this.email = novoEmail;
        }

        public void atualizarPerfil(String novoPerfil) {
                this.perfil = novoPerfil;
        }

        // =========================
        // EQUALS / HASHCODE
        // =========================

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Pessoa)) return false;
                Pessoa other = (Pessoa) o;

                if (id != null && other.id != null) {
                        return Objects.equals(id, other.id);
                }

                return Objects.equals(cpf, other.cpf);
        }

        @Override
        public int hashCode() {
                if (id != null) {
                        return Objects.hash(id);
                }
                return Objects.hash(cpf);
        }

        @Override
        public String toString() {
                return "Login{" +
                        "id=" + id +
                        ", nome='" + nome + '\'' +
                        ", cpf='" + cpf + '\'' +
                        '}';
        }
}
