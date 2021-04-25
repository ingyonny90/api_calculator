package com.api.calculator.exceptions;

abstract class CalculatorException extends RuntimeException {

    public CalculatorException(String message) {
        super(message);
    }
}
