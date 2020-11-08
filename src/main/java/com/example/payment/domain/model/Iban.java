package com.example.payment.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static java.util.Objects.requireNonNull;

@EqualsAndHashCode
@ToString
public class Iban {
    private final String value;

    private Iban(String value) {
        this.value = value;
    }

    public static Iban of(String value){
        requireNonNull(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Value '" + value + "' is not valid IBAN");
        }
        return new Iban(value);
    }

    public static boolean isValid(String value){
        //IBAN validation logic
        return true;
    }

    public String value(){
        return value;
    }
}
