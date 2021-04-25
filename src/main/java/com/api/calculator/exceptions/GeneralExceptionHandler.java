package com.api.calculator.exceptions;


import static com.api.calculator.utilities.GeneralConstants.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(CalculatorException.class)
    public String handlerException(CalculatorException exception, Model model) {
        model.addAttribute(MESSAGE_ATTRIBUTE, exception.getMessage());
        model.addAttribute(MESSAGE_STYLE, ALERT_DANGER);
        return INDEX_PAGE;
    }
}


