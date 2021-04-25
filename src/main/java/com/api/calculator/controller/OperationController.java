package com.api.calculator.controller;

import com.api.calculator.service.IUserOperationService;

import static com.api.calculator.utilities.GeneralConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class OperationController {

    @Autowired
    private IUserOperationService userOperationService;

    @RequestMapping(value = "/")
    public String indexPage() {
        return INDEX_PAGE;
    }

    @GetMapping("/token")
    public String generateToken(Model model) {
        String token = userOperationService.createUserOperation();
        model.addAttribute(MESSAGE_ATTRIBUTE, TOKEN_GENERATED_MESSAGE.concat(token));
        model.addAttribute(MESSAGE_STYLE, ALERT_SUCCESS);
        return INDEX_PAGE;
    }

    @PostMapping("/operand")
    public String addOperand(@RequestParam(value = "token") String token,
                             @RequestParam(value = "operand") String operand, Model model) {


        return INDEX_PAGE;
    }

    @GetMapping("/sum")
    public String sum() {
        return INDEX_PAGE;
    }

    @GetMapping("/subtract")
    public String subtract() {
        return INDEX_PAGE;
    }

    @GetMapping("/multiply")
    public String multiply() {
        return INDEX_PAGE;
    }

    @GetMapping("/potentiation")
    public String potentiation() {
        return INDEX_PAGE;
    }



}
