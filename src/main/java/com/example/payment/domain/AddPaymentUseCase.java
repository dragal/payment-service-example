package com.example.payment.domain;

import com.example.payment.domain.model.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddPaymentUseCase {
    private final PaymentCreator paymentCreator;
    private final PaymentRepository paymentRepository;

    public Payment execute(AddPaymentCommand command){
        Payment payment = paymentCreator.create(command);
        payment = paymentRepository.save(payment);
        return payment;
    }
}
