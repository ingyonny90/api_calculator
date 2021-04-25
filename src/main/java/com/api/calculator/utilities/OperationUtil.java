package com.api.calculator.utilities;

import com.api.calculator.exceptions.DivisionByZeroException;

import java.math.BigDecimal;
import java.util.List;

public class OperationUtil {

    public BigDecimal sum(List<BigDecimal> operandList, BigDecimal savedResult) {
        return savedResult.add(operandList.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public BigDecimal subtract(List<BigDecimal> operandList, BigDecimal savedResult) {
        return operandList.stream().reduce(BigDecimal.ZERO, BigDecimal::subtract).subtract(savedResult);
    }

    public BigDecimal multiply(List<BigDecimal> operandList, BigDecimal savedResult) {
        savedResult = isEqualsToZero(savedResult) ? BigDecimal.ONE : savedResult;
        return savedResult.multiply(operandList.stream().reduce(BigDecimal.ONE, BigDecimal::multiply));
    }

    public BigDecimal divide(List<BigDecimal> operandList, BigDecimal savedResult) {
        BigDecimal totalResult = BigDecimal.ONE;
        try {
            if (!operandList.isEmpty()) {
                totalResult = operandList.get(BigDecimal.ZERO.intValue());
                for (int index = BigDecimal.ONE.intValue(); index < operandList.size(); index++) {
                    BigDecimal newOperand = operandList.get(index);
                    totalResult = totalResult.divide(newOperand);
                }
            }
        } catch (ArithmeticException exception) {
            throw new DivisionByZeroException();
        }
        return !isEqualsToZero(savedResult) ? savedResult.divide(totalResult) : totalResult;
    }

    public BigDecimal empowerment(List<BigDecimal> operandList, BigDecimal savedResult) {
        BigDecimal totalResult = null;
        if (!operandList.isEmpty()) {
            totalResult = operandList.get(BigDecimal.ZERO.intValue());
            for (int index = BigDecimal.ONE.intValue(); index < operandList.size(); index++) {
                BigDecimal newOperand = operandList.get(index);
                totalResult = totalResult.pow(newOperand.intValue());
            }
        }
        return !isEqualsToZero(savedResult) ? savedResult.pow(totalResult.intValue()) : totalResult;
    }

    public boolean isEqualsToZero(BigDecimal value){
        return value.compareTo(BigDecimal.ZERO) == BigDecimal.ZERO.intValue();
    }
}
