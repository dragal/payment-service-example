package com.example.payment.domain;

import com.example.payment.domain.model.Iban;
import com.example.payment.domain.model.Money;
import com.example.payment.domain.model.UserId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddPaymentCommand {
    UserId userId;
    Money amount;
    Iban accountNumber;
}
