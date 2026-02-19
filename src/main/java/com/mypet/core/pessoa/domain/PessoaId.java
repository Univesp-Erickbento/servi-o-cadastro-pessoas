// java
package com.mypet.core.pessoa.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * Value object para identidade de Pessoa.
 */
public final class PessoaId {

    private final Long value;

    private PessoaId(Long value) {
        this.value = value;
    }

    public static PessoaId of(Long value) {
        Objects.requireNonNull(value, "id não pode ser nulo");
        if (value <= 0) {
            throw new IllegalArgumentException("id deve ser maior que zero");
        }
        return new PessoaId(value);
    }

    public static PessoaId empty() {
        return new PessoaId(null);
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
        if (!(o instanceof PessoaId)) return false;
        PessoaId pessoaId = (PessoaId) o;
        return Objects.equals(value, pessoaId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "PessoaId{" + value + '}';
    }
}
