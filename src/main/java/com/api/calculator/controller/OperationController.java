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
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/token")
    public String generateToken(Model model) {
        String token = userOperationService.createUserOperation();
        model.addAttribute(MESSAGE_ATTRIBUTE, TOKEN_GENERATED_MESSAGE.concat(token));
        model.addAttribute(MESSAGE_STYLE, ALERT_SUCCESS);
        return "index";
    }

    @PostMapping("/operand")
    public String addOperand(@RequestParam(value = "token") String token,
                             @RequestParam(value = "operand") String operand,Model model) {


        return "index";
    }


}
