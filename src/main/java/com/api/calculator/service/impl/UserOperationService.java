package com.api.calculator.service.impl;

import com.api.calculator.model.UserOperation;
import com.api.calculator.repo.IUserOperationRepo;
import com.api.calculator.service.IUserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserOperationService implements IUserOperationService {

    @Autowired
    private IUserOperationRepo userOperationRepo;

    @Override
    @Transactional
    public String createUserOperation() {
        return userOperationRepo.save(UserOperation.builder()
                .token(UUID.randomUUID().toString())
                .result(BigDecimal.ZERO)
                .build()).getToken();
    }
}
