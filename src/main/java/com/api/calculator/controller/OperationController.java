package com.api.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class OperationController {

    @GetMapping("/index")
    public String prueba(Model model){
      return "index";
    }
}
