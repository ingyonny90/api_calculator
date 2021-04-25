package com.api.calculator.service;

import com.api.calculator.exceptions.InvalidNumberException;
import com.api.calculator.exceptions.RequiredFieldsException;
import com.api.calculator.model.Operand;
import com.api.calculator.model.UserOperation;
import com.api.calculator.repo.IOperandRepo;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperandServiceTest {

    @MockBean
    private IOperandRepo operandRepo;

    @MockBean
    private IUserOperationService userOperationService;

    @Autowired
    private IOperandService operandService;

    @Test
    public void testSaveOperandWhenSomeParameterIsEmptyMustThrowException() {
        String token = "";
        String operand = "3";

        assertThatExceptionOfType(RequiredFieldsException.class).isThrownBy(() -> {
            operandService.saveOperand(token, operand);
        }).hasToString("com.api.calculator.exceptions.RequiredFieldsException: Todos los campos son requeridos");
    }

    @Test
    public void testSaveOperandWhenFormatIsNotValidMustThrowException() {
        String token = "sad-fasd-gas50";
        String operand = "3ds";
        UserOperation userOperation = Mockito.spy(new UserOperation());
        Mockito.doReturn(userOperation).when(userOperationService).findByToken(token);

        assertThatExceptionOfType(InvalidNumberException.class).isThrownBy(() -> {
            operandService.saveOperand(token, operand);
        }).hasToString("com.api.calculator.exceptions.InvalidNumberException: El operando debe ser un número valido");
    }

    @Test
    public void testSaveOperandMustSaveSuccessful() {
        String token = "sad-fasd-gas50";
        String operandValue = "3";
        UserOperation userOperation = Mockito.spy(new UserOperation());
        Operand operand = Mockito.spy(new Operand());
        Mockito.doReturn(userOperation).when(userOperationService).findByToken(token);
        Mockito.doReturn(operand).when(operandRepo).save(operand);

        operandService.saveOperand(token, operandValue);

        Mockito.verify(operandRepo,Mockito.times(1)).save(Mockito.any());

    }

}
