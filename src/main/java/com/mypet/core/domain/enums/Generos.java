package com.mypet.mypet.domain.enums;

public enum Generos {
    HOMEM_CIS(0, "SEXO_MASCULINO"), MULHER_CIS(1, "SEXO_FEMININO"), HOMEN_TRANS(2, "IDENTIFICA_HOMEM"),
    MULHER_TRANS(3, "IDENTIFICA_MULHER");

    private Integer codigo;
    private String descricao;

    private Generos(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Generos toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(Generos x : Generos.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Genero inválido");
    }
}
