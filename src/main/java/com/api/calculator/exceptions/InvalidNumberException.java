package com.api.calculator.exceptions;

public class InvalidNumberException extends CalculatorException{

    public InvalidNumberException() {
        super("El operando debe ser un número valido");
    }
}
