package com.mypet.core.login.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * Value Object para identidade de Login.
 */
public final class LoginId {

    private final Long value;

    private LoginId(Long value) {
        this.value = value;
    }

    public static LoginId of(Long value) {
        Objects.requireNonNull(value, "id não pode ser nulo");

        if (value <= 0) {
            throw new IllegalArgumentException("id deve ser maior que zero");
        }

        return new LoginId(value);
    }

    public static LoginId empty() {
        return new LoginId(null);
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
        if (!(o instanceof LoginId)) return false;
        LoginId loginId = (LoginId) o;
        return Objects.equals(value, loginId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "LoginId{" + value + '}';
    }
}