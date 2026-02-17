package com.mypet.mypet.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class FuncionarioDTO {
    private long pessoaId;
    private String cpf;
    private String funcionarioTipo;
    private String funcionarioReg;
    private String funcionarioStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeAdmissao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeDemissao;
}
