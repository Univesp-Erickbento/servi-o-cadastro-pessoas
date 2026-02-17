package com.mypet.mypet.domain.enums;

public enum TipoContato {
    TELEFONE(0, "CELULAR OU RESIDENCIAL"), EMAIL(1, " E-MAIL");

    private Integer codigo;
    private String descricao;

    private TipoContato(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoContato toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(TipoContato x : TipoContato.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de contato inválido");
    }
}
