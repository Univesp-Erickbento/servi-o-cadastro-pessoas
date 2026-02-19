package com.mypet.core.cliente.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * Value Object para identidade de Cliente.
 */
public final class ClienteId {

    private final Long value;

    private ClienteId(Long value) {
        this.value = value;
    }

    public static ClienteId of(Long value) {
        Objects.requireNonNull(value, "id não pode ser nulo");

        if (value <= 0) {
            throw new IllegalArgumentException("id deve ser maior que zero");
        }

        return new ClienteId(value);
    }

    public static ClienteId empty() {
        return new ClienteId(null);
    }

    @JsonValue // 🔥 ISSO RESOLVE
    public Long asLong() {
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteId)) return false;
        ClienteId that = (ClienteId) o;
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
