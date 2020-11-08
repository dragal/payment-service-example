package com.example.payment.domain;

import com.example.payment.domain.model.Payment;

class PaymentCreator {
    Payment create(AddPaymentCommand command){
        return Payment.builder()
                .userId(command.getUserId())
                .amount(command.getAmount())
                .accountNumber(command.getAccountNumber())
                .build();
    }
}
