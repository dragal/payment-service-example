package com.example.payment.domain;

import com.example.payment.domain.model.*;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdatePaymentCommand {
    PaymentId id;
    UserId userId;
    Money amount;
    Iban accountNumber;
}
