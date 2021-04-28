package com.api.calculator.controller;

import com.api.calculator.model.User;
import com.api.calculator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.api.calculator.utilities.MessageConstants.USER_SAVED_SUCCESS;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> getUserOperation(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(USER_SAVED_SUCCESS);
    }
}
