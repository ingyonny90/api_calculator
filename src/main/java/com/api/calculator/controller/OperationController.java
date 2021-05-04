package com.api.calculator.controller;


import com.api.calculator.service.IOperandService;
import com.api.calculator.service.IUserOperationService;

import static com.api.calculator.utilities.GeneralConstants.*;

import static com.api.calculator.utilities.MessageConstants.*;

import com.api.calculator.utilities.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class OperationController {

    @Autowired
    private IUserOperationService userOperationService;

    @Autowired
    private IOperandService operandService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/")
    public String indexPage() {
        return INDEX_PAGE;
    }

    @GetMapping("/token")
    public String generateToken(Model model) {
        String token = userOperationService.createUserOperation();
        model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage(TOKEN_GENERATED_PROPERTY,
                new String[]{token}, LocaleContextHolder.getLocale()));
        model.addAttribute(MESSAGE_STYLE, ALERT_SUCCESS);
        return INDEX_PAGE;
    }

    @PostMapping("/operand")
    public String addOperand(@RequestParam(value = "token") String token,
                             @RequestParam(value = "operand") String operand, Model model) {
        operandService.saveOperand(token, operand);
        model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage(OPERAND_SAVED_PROPERTY,
                null, LocaleContextHolder.getLocale()));
        model.addAttribute(MESSAGE_STYLE, ALERT_SUCCESS);
        return INDEX_PAGE;
    }

    @PostMapping("/calculate")
    public String calculateOperation(@RequestParam(value = "token") String token,
                                     @RequestParam(value = "operationType") OperatorType operationType, Model model) {

        BigDecimal result = userOperationService.calculateOperation(token, operationType);
        model.addAttribute(MESSAGE_ATTRIBUTE, messageSource.getMessage(RESULT_GENERATED_PROPERTY,
                new String[]{String.valueOf(Optional.ofNullable(result).orElse(BigDecimal.ZERO))}, LocaleContextHolder.getLocale()));
        model.addAttribute(MESSAGE_STYLE, ALERT_SUCCESS);
        return INDEX_PAGE;
    }


}
