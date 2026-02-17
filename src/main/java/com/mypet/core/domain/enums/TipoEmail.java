package com.mypet.mypet.domain.enums;

public enum TipoEmail {
    PRINCIPAL(0, "EMAIL PRINCIPAL"), EMAIL2(1, "ENDEREÇO SECUNDARIO"), EMAIL3(2, "TERCEIRO ENDEREÇO");

    private Integer codigo;
    private String descricao;

    private TipoEmail(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEmail toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(TipoEmail x : TipoEmail.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de EMAIL inválido");
    }
}
