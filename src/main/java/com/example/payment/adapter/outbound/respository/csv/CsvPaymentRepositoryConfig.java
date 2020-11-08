package com.example.payment.adapter.outbound.respository.csv;

import com.example.payment.domain.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        value = "repository.type",
        havingValue = "cvs",
        matchIfMissing = false)
public class CsvPaymentRepositoryConfig {
    @Bean
    public PaymentRepository csvPaymentRepository(@Value("${repository.csv.file-path}") final String filePath) {
        return new CsvPaymentRepository(filePath);
    }
}
