package com.example.payment.domain;

import com.example.payment.domain.model.Payment;
import com.example.payment.domain.model.PaymentId;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdatePaymentUseCase {
    private final PaymentRepository paymentRepository;

    public Payment execute(UpdatePaymentCommand command){
        PaymentId paymentId = command.getId();
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
        payment.update(command.getUserId(), command.getAccountNumber(), command.getAmount());
        return paymentRepository.save(payment);
    }
}
