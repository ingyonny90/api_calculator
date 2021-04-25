package com.api.calculator.exceptions;

public class RequiredFieldsException extends CalculatorException{

    public RequiredFieldsException() {
        super("Todos los campos son requeridos");
    }
}
