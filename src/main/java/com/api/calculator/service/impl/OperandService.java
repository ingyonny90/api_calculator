package com.api.calculator.service.impl;

import com.api.calculator.exceptions.InvalidNumberException;
import com.api.calculator.model.Operand;
import com.api.calculator.model.UserOperation;
import com.api.calculator.repo.IOperandRepo;
import com.api.calculator.service.IOperandService;
import com.api.calculator.service.IUserOperationService;
import com.api.calculator.utilities.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OperandService implements IOperandService {

    @Autowired
    private IUserOperationService userOperationService;

    @Autowired
    private IOperandRepo operandRepo;

    @Override
    @Transactional
    public void saveOperand(String token, String operand) {
        ValidationUtil.validateRequiredFields(token, operand);
        UserOperation userOperation = userOperationService.findByToken(token);
        operandRepo.save(Operand.builder()
                .active(Boolean.TRUE)
                .userOperation(userOperation)
                .operandNumber(parseStringToBigDecimal(operand))
                .build());

    }

    @Override
    public void inactiveOperand(List<Operand> operandList) {
        operandList.parallelStream().forEach(operand -> {
            operand.setActive(Boolean.FALSE);
            operandRepo.save(operand);
        });
    }

    private BigDecimal parseStringToBigDecimal(String operand) {
        try {
            return new BigDecimal(operand);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }


}
