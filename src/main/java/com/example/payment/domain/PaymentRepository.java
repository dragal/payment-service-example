package com.example.payment.domain;

import com.example.payment.domain.model.Payment;
import com.example.payment.domain.model.PaymentId;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> findById(PaymentId id);
    Payment save(Payment payment);
    List<Payment> findAll();
    void deleteById(PaymentId id);
}
