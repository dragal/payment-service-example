package com.example.payment.adapter.inbound.rest

import com.example.payment.domain.AddPaymentUseCase
import com.example.payment.domain.DeletePaymentUseCase
import com.example.payment.domain.FindAllPaymentsUseCase
import com.example.payment.domain.GetPaymentByIdUseCase
import com.example.payment.SampleCommands
import com.example.payment.domain.UpdatePaymentUseCase
import com.example.payment.domain.model.Payment
import com.example.payment.domain.model.PaymentId
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class PaymentEndpointSpec extends Specification implements SampleCommands{
    @Autowired
    MockMvc mvc

    @SpringBean
    AddPaymentUseCase addPaymentUseCase = Mock()

    @SpringBean
    GetPaymentByIdUseCase getPaymentByIdUseCase = Mock()

    @SpringBean
    FindAllPaymentsUseCase findAllPaymentsUseCase = Mock()

    @SpringBean
    UpdatePaymentUseCase updatePaymentUseCase = Mock()

    @SpringBean
    DeletePaymentUseCase deletePaymentUseCase = Mock()

    def "should add payment"() {

        given: "add payment request"
        Map request = [
                userId       : "$addOneEurPaymentCommand.userId.value",
                amount       : "$addOneEurPaymentCommand.amount.value",
                currency     : "$addOneEurPaymentCommand.amount.currency.currencyCode",
                accountNumber: "$addOneEurPaymentCommand.accountNumber.value",
        ]
        and:
        def payment = Payment.builder()
                .id(PaymentId.of("id12345"))
                .userId(addOneEurPaymentCommand.getUserId())
                .amount(addOneEurPaymentCommand.getAmount())
                .accountNumber(addOneEurPaymentCommand.getAccountNumber())
                .build()
        addPaymentUseCase.execute(addOneEurPaymentCommand) >> payment

        when:
        def result = mvc.perform(post('/payments').contentType(APPLICATION_JSON).content(toJson(request)))

        then:
        result.andExpect(status().isCreated())
        and:
        result.andExpect(jsonPath('$.id').value(payment.id.value()))
        result.andExpect(jsonPath('$.userId').value(payment.userId.value()))
        result.andExpect(jsonPath('$.accountNumber').value(payment.accountNumber.value()))
        result.andExpect(jsonPath('$.amount').value(payment.amount.value()))
        result.andExpect(jsonPath('$.currency').value(payment.amount.currency().currencyCode))

    }

}
