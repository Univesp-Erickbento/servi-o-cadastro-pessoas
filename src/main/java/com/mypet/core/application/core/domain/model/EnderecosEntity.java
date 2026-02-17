package com.mypet.mypet.application.core.domain.model;

import com.mypet.mypet.domain.enums.TipoEndereco;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "tb_Enderecos")
public class EnderecosEntity implements Serializable {

    private static final long serialVersionUID = 1l;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

//    @ManyToOne // Relacionamento muitos-para-um com Pessoa
//    @JoinColumn(name = "pessoaId") // Nome da coluna estrangeira no banco de dados
//    private Pessoa pessoaId;

    @ManyToOne // Relacionamento muitos-para-um com Cliente
    @JoinColumn(name = "clienteId") // Nome da coluna estrangeira no banco de dados
    private ClientesEntity clienteId; // Este é o nome referenciado em 'mappedBy' na classe Cliente


   // private Cliente clienteId;
    private FuncionariosEntity funcionarioId;

    private String rua;
    private String numero;
    private String complemento; // Ex: Apto, Sala, etc.
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;

    @Enumerated(EnumType.STRING) // Mapeando o enum como STRING
    private TipoEndereco tipoEndereco;

    // Getters e Setters, incluindo o tipoEndereco
    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

}
