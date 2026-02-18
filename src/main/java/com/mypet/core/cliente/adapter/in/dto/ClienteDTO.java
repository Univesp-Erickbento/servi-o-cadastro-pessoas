package com.mypet.core.cliente.adapter.in.dto;

import com.mypet.core.pessoa.domain.Pessoa;
import com.mypet.core.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private Pessoa pessoa;
    private String clienteReg;
    private Status clienteStatus;

//    public void setPessoa(long id) {
//    }
}
