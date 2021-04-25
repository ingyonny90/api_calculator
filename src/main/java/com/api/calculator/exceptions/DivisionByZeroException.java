package com.api.calculator.exceptions;

public class DivisionByZeroException extends CalculatorException{
    public DivisionByZeroException() {
        super("No se puede dividir por cero");
    }
}
