package com.mypet.mypet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String genero;
    private String perfis;
    private String email;
    private String contato;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
}
