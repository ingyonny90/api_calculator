package com.api.calculator.exceptions;

import com.api.calculator.utilities.MessageConstants;

public class UserOperationNoFoundException extends CalculatorException{

    public UserOperationNoFoundException(){
        super(MessageConstants.TOKEN_NO_FOUND_PROPERTY);
    }

}
