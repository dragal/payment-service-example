package com.example.payment.domain

import com.example.payment.adapter.outbound.respository.inmemory.InMemoryPaymentRepositoryConfig
import com.example.payment.domain.model.Iban
import com.example.payment.domain.model.Money
import com.example.payment.domain.model.UserId
import spock.lang.Specification

class AddPaymentUseCaseSpec extends Specification {
    PaymentRepository paymentRepository = new InMemoryPaymentRepositoryConfig().inMemoryPaymentRepository()
    AddPaymentUseCase addPaymentUseCase = new PaymentConfiguration().addPaymentUseCase(paymentRepository)

    def "should add payment"() {
        given: "payment details"
        def addTenDollarPaymentCommand = AddPaymentCommand.builder()
                .accountNumber(Iban.of("PL123457890"))
                .userId(UserId.of("tomekd"))
                .amount(Money.of(BigDecimal.TEN,  Currency.getInstance("USD")))
                .build()


        when: "client asks to add payment"
        def payment = addPaymentUseCase.execute(addTenDollarPaymentCommand)

        then: "module returns new payment with id"
        payment.getId() != null
        and: "with same atributes as those passed in command"
        payment.accountNumber == addTenDollarPaymentCommand.accountNumber
        payment.amount == addTenDollarPaymentCommand.amount
        payment.userId == addTenDollarPaymentCommand.userId
    }
}
