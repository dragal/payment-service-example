package com.example.payment.adapter.inbound.rest;

import com.example.payment.domain.model.Payment;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
class PaymentsResponse {
    private final List<PaymentResponse> payments;

    static PaymentsResponse of(List<Payment> payments){
        List<PaymentResponse> paymentResponses = payments.stream()
                .map(PaymentResponse::of)
                .collect(Collectors.toList());
        return new PaymentsResponse(paymentResponses);
    }
}
