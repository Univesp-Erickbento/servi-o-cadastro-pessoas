package com.mypet.core.funcionario.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * Value Object para identidade de Funcionario.
 */
public final class FuncionarioId {

    private final Long value;

    private FuncionarioId(Long value) {
        this.value = value;
    }

    public static FuncionarioId of(Long value) {
        Objects.requireNonNull(value, "id não pode ser nulo");

        if (value <= 0) {
            throw new IllegalArgumentException("id deve ser maior que zero");
        }

        return new FuncionarioId(value);
    }

    public static FuncionarioId empty() {
        return new FuncionarioId(null);
    }

    @JsonValue
    public Long asLong() {
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncionarioId)) return false;
        FuncionarioId that = (FuncionarioId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}