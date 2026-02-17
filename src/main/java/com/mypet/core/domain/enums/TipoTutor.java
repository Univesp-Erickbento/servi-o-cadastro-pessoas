package com.mypet.mypet.domain.enums;

public enum TipoTutor {
    INICANTE(0, "MENOS_1MÊS"), JUNIOR(1, "DE_2_A_6_MÊSES"), SENIOR(2, "MAIS-DE-6_MESES"),
    VIPE(3, "MAIS_DE_1_ANO");

    private Integer codigo;
    private String descricao;

    private TipoTutor(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoTutor toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(TipoTutor x : TipoTutor.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Genero inválido");
    }
}
