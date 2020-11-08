package com.example.payment.adapter.inbound.rest;

import com.example.payment.domain.PaymentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionHandlingAdvice {
    @ExceptionHandler(PaymentNotFoundException.class)
    ResponseEntity<String> handleNotFoundFilms(PaymentNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
