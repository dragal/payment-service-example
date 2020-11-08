package com.example.payment.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentConfiguration {

    @Bean
    AddPaymentUseCase addPaymentUseCase(PaymentRepository paymentRepository){
        return new AddPaymentUseCase(new PaymentCreator(), paymentRepository);
    }

    @Bean
    GetPaymentByIdUseCase getPaymentByIdUseCase(PaymentRepository paymentRepository){
        return new GetPaymentByIdUseCase(paymentRepository);
    }

    @Bean
    UpdatePaymentUseCase updatePaymentUseCase(PaymentRepository paymentRepository){
        return new UpdatePaymentUseCase(paymentRepository);
    }

    @Bean
    FindAllPaymentsUseCase findAllPaymentsUseCase(PaymentRepository paymentRepository){
        return new FindAllPaymentsUseCase(paymentRepository);
    }

    @Bean
    DeletePaymentUseCase deletePaymentUseCase(PaymentRepository paymentRepository){
        return new DeletePaymentUseCase(paymentRepository);
    }
}
