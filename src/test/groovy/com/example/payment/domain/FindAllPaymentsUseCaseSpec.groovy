package com.example.payment.domain

import com.example.payment.adapter.outbound.respository.inmemory.InMemoryPaymentRepositoryConfig
import spock.lang.Specification

import static org.hamcrest.Matchers.containsInAnyOrder
import static org.hamcrest.Matchers.hasSize

class FindAllPaymentsUseCaseSpec extends Specification implements SampleCommands {
    PaymentRepository paymentRepository = new InMemoryPaymentRepositoryConfig().paymentRepository()
    AddPaymentUseCase addPaymentUseCase = new PaymentConfiguration().addPaymentUseCase(paymentRepository)
    FindAllPaymentsUseCase findAllPaymentsUseCase = new PaymentConfiguration().findAllPaymentsUseCase(paymentRepository)

    def "should get payments"() {
        given: "module has two payments"
        def oneEurPayment = addPaymentUseCase.execute(addOneEurPaymentCommand)
        def tenDollarPayment= addPaymentUseCase.execute(addTenDollarPaymentCommand)

        when: "client asks for payments"
        def payments = findAllPaymentsUseCase.execute()

        then: "module returns two payments"
        payments hasSize(2)
        payments containsInAnyOrder(oneEurPayment, tenDollarPayment)
    }
}
