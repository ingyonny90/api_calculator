package com.api.calculator.exceptions;

import com.api.calculator.utilities.MessageConstants;

public class DivisionByZeroException extends CalculatorException{
    public DivisionByZeroException() {
        super(MessageConstants.DIVIDE_BY_ZERO_ERROR_MESSAGE);
    }
}
