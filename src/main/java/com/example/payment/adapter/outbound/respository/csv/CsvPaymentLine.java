package com.example.payment.adapter.outbound.respository.csv;

import com.example.payment.domain.AddPaymentCommand;
import com.example.payment.domain.model.*;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvPaymentLine {
    @CsvBindByPosition(position = 0, required = true)
    private String id;

    @CsvBindByPosition(position = 1, required = true)
    private String userId;

    @CsvBindByPosition(position = 2, required = true)
    private String accountNumber;

    @CsvBindByPosition(position = 3, required = true)
    private BigDecimal amount;

    @CsvBindByPosition(position = 4, required = true)
    private String currency;

    static CsvPaymentLine of(Payment payment){
        return CsvPaymentLine.builder()
                .id(payment.getId().value())
                .userId(payment.getUserId().value())
                .amount(payment.getAmount().value())
                .currency(payment.getAmount().currency().getCurrencyCode())
                .accountNumber(payment.getAccountNumber().value())
                .build();
    }

    Payment toPayment(){
        return Payment.builder()
                .id(PaymentId.of(id))
                .userId(UserId.of(userId))
                .amount(Money.of(amount, Currency.getInstance(currency)))
                .accountNumber(Iban.of(accountNumber))
                .build();
    }
}
