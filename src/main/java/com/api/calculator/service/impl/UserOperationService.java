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
import java.util.ArrayList;
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
        List<BigDecimal> operands = addSavedResultResultOnOperandList(userOperation.getResult(),
                convertOperandToBigDecimalList(activeOperandList));
        BigDecimal result = getResultByOperationType(operands, operationType);
        userOperation.setResult(result);
        userOperationRepo.save(userOperation);
        operandService.inactiveOperand(activeOperandList);
        return result;
    }

    private List<BigDecimal> addSavedResultResultOnOperandList(BigDecimal savedResult, List<BigDecimal> operands) {
        if (savedResult != null) {
            List<BigDecimal> newOperandList = new ArrayList<BigDecimal>();
            newOperandList.add(savedResult);
            newOperandList.addAll(operands);
            return  newOperandList;
        }
        return operands;
    }

    public BigDecimal getResultByOperationType(List<BigDecimal> operandList, OperatorType operationType) {
        BigDecimal result = null;
        if (!operandList.isEmpty()) {
            OperationUtil operationUtil = new OperationUtil();

            switch (operationType) {
                case SUM:
                    result = operationUtil.sum(operandList);
                    break;
                case SUBTRACT:
                    result = operationUtil.subtract(operandList);
                    break;
                case MULTIPLY:
                    result = operationUtil.multiply(operandList);
                    break;
                case DIVIDE:
                    result = operationUtil.divide(operandList);
                    break;
                case EMPOWERMENT:
                    result = operationUtil.empowerment(operandList);
                    break;
            }
        }
        return result;
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
