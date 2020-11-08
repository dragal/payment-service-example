package com.example.payment.adapter.inbound.rest;

import com.example.payment.domain.model.Payment;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PaymentResponse {
    private final String id;
    private final String userId;
    private final BigDecimal amount;
    private final String currency;
    private final String accountNumber;

    static PaymentResponse of(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId().value())
                .userId(payment.getUserId().value())
                .amount(payment.getAmount().value())
                .currency(payment.getAmount().currency().getCurrencyCode())
                .accountNumber(payment.getAccountNumber().value())
                .build();
    }
}
