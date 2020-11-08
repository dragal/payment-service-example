package com.example.payment.domain

import com.example.payment.SampleCommands
import com.example.payment.adapter.outbound.respository.inmemory.InMemoryPaymentRepositoryConfig
import com.example.payment.domain.model.Iban
import com.example.payment.domain.model.Money
import com.example.payment.domain.model.UserId
import spock.lang.Specification

class UpdatePaymentUseCaseSpec extends Specification implements SampleCommands{
    PaymentRepository paymentRepository = new InMemoryPaymentRepositoryConfig().inMemoryPaymentRepository()
    AddPaymentUseCase addPaymentUseCase = new PaymentConfiguration().addPaymentUseCase(paymentRepository)
    UpdatePaymentUseCase updatePaymentUseCase = new PaymentConfiguration().updatePaymentUseCase(paymentRepository)

    def "should update payment"() {
        given: "module has payment"
        def givenTenDollarPayment = addPaymentUseCase.execute(addTenDollarPaymentCommand)
        def paymentId = givenTenDollarPayment.getId()
        and: "client wants to update that payment"
        def updatePaymentCommand = UpdatePaymentCommand.builder()
                .id(paymentId)
                .amount(Money.of(BigDecimal.ONE, Currency.getInstance("USD")))
                .userId(UserId.of("stefan"))
                .accountNumber(Iban.of("PL098765421"))
                .build()

        when: "client asks to update payment"
        def updatedPayment = updatePaymentUseCase.execute(updatePaymentCommand)

        then: "module returns updated payment"
        updatedPayment.getId() == givenTenDollarPayment.getId()
    }
}
