package com.psu.est.controller;

import com.psu.est.service.exception.InvalidAddressException;
import com.psu.est.service.exception.ValidationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by danielkarkee on 3/27/16.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Constraint integrity violation")
    @ExceptionHandler(value = ConstraintViolationException.class)
    public void handleConstraintViolationException(){}

    @ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason="Cannot validate resource")
    @ExceptionHandler(value = ValidationException.class)
    public void handleValidationException(){}

    @ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason="Address not valid")
    @ExceptionHandler(value = InvalidAddressException.class)
    public void handleInValidAddressException(){}
}
