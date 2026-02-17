package com.mypet.mypet.domain.enums;

public enum PerfisEnum {
    ADMIN(0, "ROLE_ADMIN"), TUTOR(1, "ROLE_TUTOR"), FUNCIONARIO(2, "ROLE_FUNCIONARIO");

    private Integer codigo;
    private String descricao;

    private PerfisEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PerfisEnum toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(PerfisEnum x : PerfisEnum.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Perfil inválido");
    }
}
