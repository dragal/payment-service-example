package com.example.payment.domain;

import com.example.payment.domain.model.Payment;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllPaymentsUseCase {
    private final PaymentRepository paymentRepository;

    public List<Payment> execute(){
        return paymentRepository.findAll();
    }
}
