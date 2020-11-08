package com.example.payment.adapter.outbound.respository.inmemory;

import com.example.payment.domain.PaymentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        value = "repository.type",
        havingValue = "inmemory",
        matchIfMissing = true)
public class InMemoryPaymentRepositoryConfig {
    @Bean
    public PaymentRepository inMemoryPaymentRepository() {
        return new InMemoryPaymentRepository();
    }
}
