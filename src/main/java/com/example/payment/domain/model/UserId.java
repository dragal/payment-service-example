package com.example.payment.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static java.util.Objects.requireNonNull;

@EqualsAndHashCode
@ToString
public class UserId {
    private final String value;

    private UserId(String value) {
        this.value = value;
    }

    public static UserId of(String value) {
        requireNonNull(value);
        return new UserId(value);
    }

    public String value() {
        return value;
    }
}
