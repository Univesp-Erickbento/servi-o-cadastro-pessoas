package com.mypet.mypet.domain.dto.clientedto;

import com.mypet.mypet.application.core.domain.model.PessoasEntity;
import com.mypet.mypet.domain.enums.Status;
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
    private PessoasEntity pessoa;
    private String clienteReg;
    private Status clienteStatus;

//    public void setPessoa(long id) {
//    }
}
