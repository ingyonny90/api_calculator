package com.api.calculator.exceptions;

public class UserOperationNoFoundException extends CalculatorException{

    public UserOperationNoFoundException(){
        super("No se encontró token asociado");
    }

}
