package com.api.calculator.service;

import com.api.calculator.model.Operand;

import java.util.List;

public interface IOperandService {

    void saveOperand(String token,String operand);
    void inactiveOperand(List<Operand> operandList);
}
