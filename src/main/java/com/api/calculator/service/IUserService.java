package com.api.calculator.service;

import com.api.calculator.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    void createUser(User user);
}
