package com.example.payment.adapter.inbound.rest;

import com.example.payment.domain.*;
import com.example.payment.domain.model.Payment;
import com.example.payment.domain.model.PaymentId;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
class PaymentEndpoint {
    private final AddPaymentUseCase addPaymentUseCase;
    private final GetPaymentByIdUseCase getPaymentByIdUseCase;
    private final FindAllPaymentsUseCase findAllPaymentsUseCase;
    private final UpdatePaymentUseCase updatePaymentUseCase;
    private final DeletePaymentUseCase deletePaymentUseCase;

    @ApiOperation("Add payment")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PaymentResponse add(@RequestBody @Valid AddPaymentRequest request) {
        Payment payment = addPaymentUseCase.execute(request.toCommand());
        return PaymentResponse.of(payment);
    }

    @ApiOperation("Get payment")
    @GetMapping("{id}")
    PaymentResponse find(@PathVariable String id) {
        Payment payment = getPaymentByIdUseCase.execute(PaymentId.of(id));
        return PaymentResponse.of(payment);
    }

    @ApiOperation("Find all payments")
    @GetMapping
    PaymentsResponse findAll() {
        List<Payment> payments = findAllPaymentsUseCase.execute();
        return PaymentsResponse.of(payments);
    }

    @ApiOperation("Update payment")
    @PutMapping
    PaymentResponse update(@RequestBody @Valid UpdatePaymentRequest request) {
        Payment payment = updatePaymentUseCase.execute(request.toCommand());
        return PaymentResponse.of(payment);
    }

    @ApiOperation("Delete payment")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        deletePaymentUseCase.execute(PaymentId.of(id));
    }
}
