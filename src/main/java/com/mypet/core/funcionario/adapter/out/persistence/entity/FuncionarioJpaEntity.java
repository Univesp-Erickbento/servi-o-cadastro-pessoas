package com.mypet.core.funcionario.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_funcionarios")
public class FuncionarioJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;

    @Column(name = "funcionario_tipo")
    private String funcionarioTipo;

    @Column(name = "funcionario_reg")
    private String funcionarioReg;

    @Column(name = "funcionario_status")
    private String funcionarioStatus;

    @Column(name = "data_de_admissao")
    private LocalDate dataDeAdmissao;

    @Column(name = "data_de_demissao")
    private LocalDate dataDeDemissao;

    public FuncionarioJpaEntity() {
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getFuncionarioTipo() {
        return funcionarioTipo;
    }

    public void setFuncionarioTipo(String funcionarioTipo) {
        this.funcionarioTipo = funcionarioTipo;
    }

    public String getFuncionarioReg() {
        return funcionarioReg;
    }

    public void setFuncionarioReg(String funcionarioReg) {
        this.funcionarioReg = funcionarioReg;
    }

    public String getFuncionarioStatus() {
        return funcionarioStatus;
    }

    public void setFuncionarioStatus(String funcionarioStatus) {
        this.funcionarioStatus = funcionarioStatus;
    }

    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(LocalDate dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public LocalDate getDataDeDemissao() {
        return dataDeDemissao;
    }

    public void setDataDeDemissao(LocalDate dataDeDemissao) {
        this.dataDeDemissao = dataDeDemissao;
    }
}