package com.mypet.core.funcionario.domain;

import com.mypet.core.pessoa.domain.PessoaId;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade de domínio Funcionario (sem anotações JPA).
 */
public class Funcionario {

    private FuncionarioId id;
    private PessoaId pessoaId;
    private String funcionarioTipo;
    private String funcionarioReg;
    private String funcionarioStatus;
    private LocalDate dataDeAdmissao;
    private LocalDate dataDeDemissao;

    private Funcionario() {
        // construtor protegido para mapeadores
    }

    public Funcionario(
            FuncionarioId id,
            PessoaId pessoaId,
            String funcionarioTipo,
            String funcionarioReg,
            String funcionarioStatus,
            LocalDate dataDeAdmissao,
            LocalDate dataDeDemissao
    ) {

        Objects.requireNonNull(pessoaId, "pessoaId é obrigatório");

        this.id = id;
        this.pessoaId = pessoaId;
        this.funcionarioTipo = funcionarioTipo;
        this.funcionarioReg = funcionarioReg;
        this.funcionarioStatus = funcionarioStatus;
        this.dataDeAdmissao = dataDeAdmissao;
        this.dataDeDemissao = dataDeDemissao;
    }

    /**
     * Factory para criação de novo Funcionario
     */
    public static Funcionario novo(
            PessoaId pessoaId,
            String funcionarioTipo,
            String funcionarioReg,
            LocalDate dataDeAdmissao
    ) {
        return new Funcionario(
                FuncionarioId.empty(),
                pessoaId,
                funcionarioTipo,
                funcionarioReg,
                "ATIVO",
                dataDeAdmissao,
                null
        );
    }

    // =========================
    // GETTERS
    // =========================

    public FuncionarioId getId() {
        return id;
    }

    public void setId(FuncionarioId id) {
        this.id = id;
    }

    public PessoaId getPessoaId() {
        return pessoaId;
    }

    public String getFuncionarioTipo() {
        return funcionarioTipo;
    }

    public String getFuncionarioReg() {
        return funcionarioReg;
    }

    public String getFuncionarioStatus() {
        return funcionarioStatus;
    }

    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public LocalDate getDataDeDemissao() {
        return dataDeDemissao;
    }

    // =========================
    // REGRAS DE NEGÓCIO
    // =========================

    public void ativar() {
        this.funcionarioStatus = "ATIVO";
        this.dataDeDemissao = null;
    }

    public void inativar(LocalDate dataDemissao) {
        this.funcionarioStatus = "INATIVO";
        this.dataDeDemissao = dataDemissao;
    }

    public void alterarRegistro(String novoRegistro) {
        this.funcionarioReg = novoRegistro;
    }

    public boolean isNovo() {
        return id == null || !id.isPresent();
    }

    // =========================
    // EQUALS / HASHCODE
    // =========================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario)) return false;
        Funcionario other = (Funcionario) o;

        if (id != null && id.isPresent()
                && other.id != null && other.id.isPresent()) {
            return Objects.equals(id, other.id);
        }

        return Objects.equals(pessoaId, other.pessoaId);
    }

    @Override
    public int hashCode() {
        if (id != null && id.isPresent()) {
            return Objects.hash(id);
        }
        return Objects.hash(pessoaId);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", pessoaId=" + pessoaId +
                ", status=" + funcionarioStatus +
                '}';
    }
}