package com.mypet.core.pessoa.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * Entidade de domínio PessoaEntity (sem anotações JPA).
 */
public class PessoaEntity {

        private PessoaId id;
        private String nome;
        private String sobrenome;
        private String cpf;
        private String rg;
        private String genero;
        private Set<String> perfis = new LinkedHashSet<>();
        private String email;
        private String contato;
        private LocalDate dataNascimento;
        private LocalDate dataCadastro;

        private PessoaEntity() {
                // construtor para uso interno / mapeadores
        }

        public PessoaEntity(PessoaId id,
                            String nome,
                            String sobrenome,
                            String cpf,
                            String rg,
                            String genero,
                            Collection<String> perfis,
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

                if (perfis != null) {
                        this.perfis = new LinkedHashSet<>(perfis);
                }

                this.email = email;
                this.contato = contato;
                this.dataNascimento = dataNascimento;
                this.dataCadastro = dataCadastro != null ? dataCadastro : LocalDate.now();
        }

        public static PessoaEntity of(PessoaId id,
                                      String nome,
                                      String sobrenome,
                                      String cpf,
                                      LocalDate dataNascimento) {

                return new PessoaEntity(
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

        public Set<String> getPerfis() {
                return Collections.unmodifiableSet(perfis);
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
                Collection<String> perfis,
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

                this.perfis.clear();
                if (perfis != null) {
                        this.perfis.addAll(perfis);
                }

                this.email = email;
                this.contato = contato;
                this.dataNascimento = dataNascimento;
        }

        // =========================
        // MÉTODOS DE DOMÍNIO ESPECÍFICOS
        // =========================

        public void addPerfil(String perfil) {
                if (perfil != null && !perfil.isBlank()) {
                        perfis.add(perfil.trim());
                }
        }

        public void removePerfil(String perfil) {
                perfis.remove(perfil);
        }

        public void atualizarContato(String novoContato) {
                this.contato = novoContato;
        }

        public void atualizarEmail(String novoEmail) {
                this.email = novoEmail;
        }

        // =========================
        // EQUALS / HASHCODE
        // =========================

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof PessoaEntity)) return false;
                PessoaEntity other = (PessoaEntity) o;

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
                return "PessoaEntity{" +
                        "id=" + id +
                        ", nome='" + nome + '\'' +
                        ", cpf='" + cpf + '\'' +
                        '}';
        }
}
