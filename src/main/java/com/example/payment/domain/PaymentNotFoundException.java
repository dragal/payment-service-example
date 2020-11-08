package com.example.payment.domain;

import com.example.payment.domain.model.PaymentId;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(PaymentId id) {
        super("Payment with id: " + id.value() + " not exists.");
    }
}
