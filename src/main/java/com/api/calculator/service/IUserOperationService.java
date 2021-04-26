package com.api.calculator.service;

import com.api.calculator.model.UserOperation;
import com.api.calculator.utilities.OperatorType;

import java.math.BigDecimal;
import java.util.List;


public interface IUserOperationService {

    String createUserOperation();
    UserOperation findByToken(String token);
    BigDecimal calculateOperation(String token, OperatorType operationType);
    List<UserOperation> findAllUserOperation();
}
