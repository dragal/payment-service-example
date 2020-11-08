package com.example.payment.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Currency;

import static java.util.Objects.requireNonNull;

/**
 * Take a look at :
 * - https://javamoney.github.io/
 * - https://www.joda.org/joda-money/
 */
@EqualsAndHashCode
@ToString
public class Money {
    private final BigDecimal value;
    private final Currency currency;

    private Money(BigDecimal value, Currency currency) {
        requireNonNull(value);
        requireNonNull(currency);
        this.value = value;
        this.currency = currency;
    }

    public static Money of(BigDecimal value, Currency currency) {
        return new Money(value, currency);
    }

    public BigDecimal value(){
        return value;
    }

    public Currency currency(){
        return currency;
    }
}
