package com.psu.est.controller;

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
}
