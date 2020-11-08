package com.example.payment.domain

import com.example.payment.SampleCommands
import com.example.payment.adapter.outbound.respository.csv.CsvPaymentRepositoryConfig
import spock.lang.AutoCleanup
import spock.lang.Specification

import java.nio.file.Files
import java.util.stream.Collectors

class AddPaymentUseCaseCsvSpec extends Specification implements SampleCommands{
    @AutoCleanup("delete")
    File csvFile = File.createTempFile("payments", ".csv")
    PaymentRepository paymentRepository = new CsvPaymentRepositoryConfig().csvPaymentRepository(csvFile.getAbsolutePath())
    AddPaymentUseCase addPaymentUseCase = new PaymentConfiguration().addPaymentUseCase(paymentRepository)

    def "should add payment"() {
        given: "payment details"
        AddPaymentCommand  paymentCommand = addTenDollarPaymentCommand

        when: "client asks to add payment"
        def payment = addPaymentUseCase.execute(paymentCommand)

        then: "module returns new payment with id"
        payment.getId() != null
        and: "with same atributes as those passed in command"
        payment.accountNumber == paymentCommand.accountNumber
        payment.amount == paymentCommand.amount
        payment.userId == paymentCommand.userId
        and: "payment saved to csv file"
        def paymentLine = Files.lines(csvFile.toPath()).findFirst().get()
        def expectedLine = toCvsLine(payment.getId().value(), paymentCommand)
        expectedLine == paymentLine
    }

    def toCvsLine(String id, AddPaymentCommand command) {
        def addQuotes = { value -> "\"" + value + "\"" }
        return Arrays.asList(id, command.getUserId().value(), command.accountNumber.value(), command.amount.value(), command.amount.currency().currencyCode)
                .stream().map(addQuotes)
                .collect(Collectors.joining(","))
    }
}
