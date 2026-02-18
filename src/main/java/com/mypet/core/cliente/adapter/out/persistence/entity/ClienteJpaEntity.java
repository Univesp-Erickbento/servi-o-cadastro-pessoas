package com.mypet.core.cliente.adapter.out.persistence.entity;

import com.mypet.core.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_clientes")
public class ClienteJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;

    @Column(name = "cliente_reg")
    private String clienteReg;

    @Enumerated(EnumType.STRING)
    @Column(name = "cliente_status")
    private Status clienteStatus;
}
