package com.example.payment.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static java.util.Objects.requireNonNull;

@EqualsAndHashCode
@ToString
public class PaymentId {
    private final String value;

    private PaymentId(String value) {
        this.value = value;
    }

    public static PaymentId of(String value) {
        requireNonNull(value);
        return new PaymentId(value);
    }

    public String value(){
        return value;
    }
}
