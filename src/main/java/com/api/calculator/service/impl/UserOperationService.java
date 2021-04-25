package com.api.calculator.service.impl;

import com.api.calculator.exceptions.UserOperationNoFoundException;
import com.api.calculator.model.Operand;
import com.api.calculator.model.UserOperation;
import com.api.calculator.repo.IUserOperationRepo;
import com.api.calculator.service.IUserOperationService;
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
        BigDecimal result = getResultByOperationType(filterActiveOperand(userOperation.getOperandList())
                , operationType, userOperation.getResult());
        userOperation.setResult(result);
        userOperationRepo.save(userOperation);
        return result;
    }

    private BigDecimal getResultByOperationType(List<BigDecimal> operandList, OperatorType operationType, BigDecimal currentResult) {
        switch (operationType) {
            case SUM:
                currentResult = operandList.stream().reduce(BigDecimal.ZERO,BigDecimal::add).add(currentResult);
                break;
            case SUBTRACT:
                //result = operands.stream().reduce(BigDecimal::subtract).orElseThrow(OperationNotAllowed::new);
                break;
            case MULTIPLY:
                //result = operands.stream().reduce(BigDecimal.ONE, BigDecimal::multiply);
                break;
            case DIVIDE:
                //result = divide(operands);
                break;
            case EMPOWERMENT:
                //result = exp(operands);
                break;
        }

        return currentResult;
    }

    private List<BigDecimal> filterActiveOperand(List<Operand> operandList) {
        return operandList.stream().
                filter(operand -> operand.isActive()).map(Operand::getOperandNumber)
                .collect(Collectors.toList());
    }
}
