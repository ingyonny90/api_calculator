package com.api.calculator.exceptions;

import com.api.calculator.utilities.MessageConstants;

public class RequiredFieldsException extends CalculatorException{

    public RequiredFieldsException() {
        super(MessageConstants.REQUIRED_FIELD_ERROR_MESSAGE);
    }
}
