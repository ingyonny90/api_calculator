package com.api.calculator.exceptions;


import static com.api.calculator.utilities.GeneralConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(CalculatorException.class)
    public String handlerException(CalculatorException exception, Model model) {
        model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
        model.addAttribute(MESSAGE_STYLE, ALERT_DANGER);
        return INDEX_PAGE;
    }
}


