package com.example.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Payment {
    private final PaymentId id;
    private UserId userId;
    private Money amount;
    private Iban accountNumber;

    public void update(UserId userId, Iban accountNumber, Money amount) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
}
