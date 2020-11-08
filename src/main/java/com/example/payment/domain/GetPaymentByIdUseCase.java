package com.example.payment.domain;

import com.example.payment.domain.model.Payment;
import com.example.payment.domain.model.PaymentId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetPaymentByIdUseCase {
    private final PaymentRepository paymentRepository;

    public Payment execute(PaymentId id){
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
    }
}
