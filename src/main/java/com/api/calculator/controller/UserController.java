package com.api.calculator.controller;

import com.api.calculator.model.User;
import com.api.calculator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
