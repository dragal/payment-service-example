package com.example.payment.domain

import com.example.payment.SampleCommands
import com.example.payment.adapter.outbound.respository.inmemory.InMemoryPaymentRepositoryConfig
import com.example.payment.domain.model.PaymentId
import spock.lang.Specification

class GetPaymentByIdUseCaseSpec extends Specification implements SampleCommands{
    PaymentRepository paymentRepository = new InMemoryPaymentRepositoryConfig().inMemoryPaymentRepository()
    AddPaymentUseCase addPaymentUseCase = new PaymentConfiguration().addPaymentUseCase(paymentRepository)
    GetPaymentByIdUseCase getPaymentByIdUseCase = new PaymentConfiguration().getPaymentByIdUseCase(paymentRepository)

    def "should get payment"() {
        given: "module has payment"
        def givenOneEurPayment = addPaymentUseCase.execute(addOneEurPaymentCommand)
        def paymentId = givenOneEurPayment.getId()

        when: "client asks for payment"
        def returnedPayment = getPaymentByIdUseCase.execute(paymentId)

        then: "module returns new payment"
        returnedPayment == givenOneEurPayment
    }

    def "should throw exception if payment not found"() {
        given: "module has no payment with given id"
        def paymentId = PaymentId.of ("testid")

        when: "client asks for payment"
        getPaymentByIdUseCase.execute(paymentId)

        then: "module throws exception"
        thrown PaymentNotFoundException
    }
}
