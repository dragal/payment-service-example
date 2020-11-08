package com.example.payment

import com.example.payment.domain.AddPaymentCommand
import com.example.payment.domain.model.Iban
import com.example.payment.domain.model.Money
import com.example.payment.domain.model.UserId
import groovy.transform.SelfType
import spock.lang.Specification

@SelfType(Specification)
trait SampleCommands {
    AddPaymentCommand addTenDollarPaymentCommand = AddPaymentCommand.builder()
            .accountNumber(Iban.of("PL123457890"))
            .userId(UserId.of("tomekd"))
            .amount(Money.of(BigDecimal.TEN, Currency.getInstance("USD")))
            .build();
    AddPaymentCommand addOneEurPaymentCommand = AddPaymentCommand.builder()
            .accountNumber(Iban.of("PL123457890"))
            .userId(UserId.of("tomekd"))
            .amount(Money.of(BigDecimal.ONE, Currency.getInstance("USD")))
            .build();

}
