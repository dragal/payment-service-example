package com.example.payment.domain;

import com.example.payment.domain.model.PaymentId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePaymentUseCase {
    private final PaymentRepository paymentRepository;

    public void execute(PaymentId id) {
        paymentRepository.deleteById(id);
    }
}
