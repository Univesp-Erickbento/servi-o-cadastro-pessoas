package com.mypet.mypet.application.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mypet.mypet.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_Funcionarios")
public class FuncionariosEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pessoa_id", nullable = false)
    private long pessoaId;

    private String funcionarioTipo;
    private String funcionarioReg;

  //  @Enumerated(EnumType.STRING)
   // private Status funcionarioStatus;

    private String funcionarioStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_admissao")
    private LocalDate dataDeAdmissao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_de_demissao")
    private LocalDate dataDeDemissao;
}
