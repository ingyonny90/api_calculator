package com.api.calculator.exceptions;

import com.api.calculator.utilities.MessageConstants;

public class InvalidNumberException extends CalculatorException{

    public InvalidNumberException() {
        super(MessageConstants.OPERAND_NO_VALID_ERROR_MESSAGE);
    }
}
