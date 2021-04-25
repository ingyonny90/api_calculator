package com.api.calculator.service.impl;

import com.api.calculator.exceptions.UserOperationNoFoundException;
import com.api.calculator.model.Operand;
import com.api.calculator.model.UserOperation;
import com.api.calculator.repo.IUserOperationRepo;
import com.api.calculator.service.IOperandService;
import com.api.calculator.service.IUserOperationService;
import com.api.calculator.utilities.OperationUtil;
import com.api.calculator.utilities.OperatorType;
import com.api.calculator.utilities.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserOperationService implements IUserOperationService {

    @Autowired
    private IUserOperationRepo userOperationRepo;

    @Autowired
    private IOperandService operandService;

    @Override
    @Transactional
    public String createUserOperation() {
        return userOperationRepo.save(UserOperation.builder()
                .token(UUID.randomUUID().toString())
                .result(BigDecimal.ZERO)
                .build()).getToken();
    }

    @Override
    public UserOperation findByToken(String token) {
        return userOperationRepo.findByToken(token)
                .orElseThrow(UserOperationNoFoundException::new);
    }

    @Override
    public BigDecimal calculateOperation(String token, OperatorType operationType) {
        ValidationUtil.validateRequiredFields(token);
        UserOperation userOperation = findByToken(token);
        List<Operand> activeOperandList = filterActiveOperand(userOperation.getOperandList());
        BigDecimal result = getResultByOperationType(convertOperandToBigDecimalList(activeOperandList)
                , operationType, userOperation.getResult());
        userOperation.setResult(result);
        userOperationRepo.save(userOperation);
        operandService.inactiveOperand(activeOperandList);
        return result;
    }

    private BigDecimal getResultByOperationType(List<BigDecimal> operandList, OperatorType operationType, BigDecimal currentResult) {
        OperationUtil operationUtil = new OperationUtil();
        switch (operationType) {
            case SUM:
                currentResult = operationUtil.sum(operandList,currentResult);
                break;
            case SUBTRACT:
                currentResult = operationUtil.subtract(operandList,currentResult);
                break;
            case MULTIPLY:
                currentResult = operationUtil.multiply(operandList,currentResult);
                break;
            case DIVIDE:
                currentResult = operationUtil.divide(operandList,currentResult);
                break;
            case EMPOWERMENT:
                currentResult = operationUtil.empowerment(operandList,currentResult);
                break;
        }

        return currentResult;
    }

    private List<Operand> filterActiveOperand(List<Operand> operandList) {
        return operandList.stream().
                filter(operand -> operand.isActive())
                .collect(Collectors.toList());
    }

    private List<BigDecimal> convertOperandToBigDecimalList(List<Operand> operandList) {
        return operandList.stream().map(Operand::getOperandNumber)
                .collect(Collectors.toList());
    }
}
