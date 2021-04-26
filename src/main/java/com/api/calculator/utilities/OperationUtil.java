package com.api.calculator.utilities;

import com.api.calculator.exceptions.DivisionByZeroException;

import java.math.BigDecimal;
import java.util.List;

public class OperationUtil {

    public BigDecimal sum(List<BigDecimal> operandList) {
        return operandList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal subtract(List<BigDecimal> operandList) {
        BigDecimal totalResult = operandList.get(BigDecimal.ZERO.intValue());
        for (int index = BigDecimal.ONE.intValue(); index < operandList.size(); index++) {
            BigDecimal newOperand = operandList.get(index);
            totalResult = totalResult.subtract(newOperand);
        }
        return totalResult;
    }

    public BigDecimal multiply(List<BigDecimal> operandList) {
        return operandList.stream().reduce(BigDecimal.ONE, BigDecimal::multiply);
    }

    public BigDecimal divide(List<BigDecimal> operandList) {
        BigDecimal totalResult = operandList.get(BigDecimal.ZERO.intValue());
        try {
            for (int index = BigDecimal.ONE.intValue(); index < operandList.size(); index++) {
                BigDecimal newOperand = operandList.get(index);
                totalResult = totalResult.divide(newOperand);
            }
        } catch (ArithmeticException exception) {
            throw new DivisionByZeroException();
        }
        return totalResult;
    }

    public BigDecimal empowerment(List<BigDecimal> operandList) {
        BigDecimal totalResult = operandList.get(BigDecimal.ZERO.intValue());
        for (int index = BigDecimal.ONE.intValue(); index < operandList.size(); index++) {
            BigDecimal newOperand = operandList.get(index);
            totalResult = totalResult.pow(newOperand.intValue());
        }

        return totalResult;
    }


}
