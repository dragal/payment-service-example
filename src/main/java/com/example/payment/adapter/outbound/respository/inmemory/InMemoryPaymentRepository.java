package com.example.payment.adapter.outbound.respository.inmemory;

import com.example.payment.domain.PaymentRepository;
import com.example.payment.domain.model.Payment;
import com.example.payment.domain.model.PaymentId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryPaymentRepository implements PaymentRepository {

    private final ConcurrentHashMap<String, Payment> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<Payment> findById(PaymentId id) {
        Payment payment = storage.get(id.value());
        return Optional.ofNullable(payment);
    }

    @Override
    public Payment save(Payment payment) {
        if (isNew(payment)) {
            payment = Payment.builder()
                    .id(PaymentId.of(UUID.randomUUID().toString()))
                    .accountNumber(payment.getAccountNumber())
                    .amount(payment.getAmount())
                    .userId(payment.getUserId())
                    .amount(payment.getAmount())
                    .build();
        }
        storage.put(payment.getId().value(), payment);
        return payment;
    }




    private boolean isNew(Payment payment) {
        return payment.getId() == null;
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(PaymentId id) {
        storage.remove(id.value());
    }
}
