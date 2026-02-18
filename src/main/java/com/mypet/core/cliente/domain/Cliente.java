package com.mypet.core.cliente.domain;

import com.mypet.core.domain.enums.Status;
import com.mypet.core.pessoa.domain.PessoaId;

import java.util.Objects;

/**
 * Entidade de domínio Cliente (sem anotações JPA).
 */
public class Cliente {

    private ClienteId id;
    private PessoaId pessoaId;
    private String clienteReg;
    private Status status;

    private Cliente() {
        // construtor protegido para mapeadores
    }

    public Cliente(
            ClienteId id,
            PessoaId pessoaId,
            String clienteReg,
            Status status
    ) {

        Objects.requireNonNull(pessoaId, "pessoaId é obrigatório");

        this.id = id;
        this.pessoaId = pessoaId;
        this.clienteReg = clienteReg;
        this.status = status != null ? status : Status.ATIVO;
    }

    /**
     * Factory para criação de novo Cliente
     */
    public static Cliente novo(PessoaId pessoaId, String clienteReg) {
        return new Cliente(
                ClienteId.empty(),
                pessoaId,
                clienteReg,
                Status.ATIVO
        );
    }

    // =========================
    // GETTERS
    // =========================

    public ClienteId getId() {
        return id;
    }

    public void setId(ClienteId id) {
        this.id = id;
    }

    public PessoaId getPessoaId() {
        return pessoaId;
    }

    public String getClienteReg() {
        return clienteReg;
    }

    public Status getStatus() {
        return status;
    }

    // =========================
    // REGRAS DE NEGÓCIO
    // =========================

    public void ativar() {
        this.status = Status.ATIVO;
    }

    public void inativar() {
        this.status = Status.INATIVO;
    }

    public void alterarRegistro(String novoRegistro) {
        this.clienteReg = novoRegistro;
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
        if (!(o instanceof Cliente)) return false;
        Cliente other = (Cliente) o;

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
        return "Cliente{" +
                "id=" + id +
                ", pessoaId=" + pessoaId +
                ", status=" + status +
                '}';
    }
}
