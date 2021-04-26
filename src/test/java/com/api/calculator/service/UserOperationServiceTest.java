package com.api.calculator.service;

import com.api.calculator.service.impl.UserOperationService;
import com.api.calculator.utilities.OperatorType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserOperationServiceTest {

    @Autowired
    private UserOperationService userOperationService;

    @Test
    public void testGetResultByOperationTypeWhenOperationTypeIsSum(){
        List<BigDecimal> operandList = new ArrayList<BigDecimal>();
        operandList.add(new BigDecimal(2));
        operandList.add(new BigDecimal(5));
        OperatorType operationType = OperatorType.SUM;
        BigDecimal savedResult = BigDecimal.ZERO;

        BigDecimal result = userOperationService.getResultByOperationType(operandList,operationType,savedResult);

        assertThat(result).isEqualTo(new BigDecimal(7));
    }

    @Test
    public void testGetResultByOperationTypeWhenOperationTypeIsSubtract(){
        List<BigDecimal> operandList = new ArrayList<BigDecimal>();
        operandList.add(new BigDecimal(2));
        operandList.add(new BigDecimal(5));
        OperatorType operationType = OperatorType.SUBTRACT;
        BigDecimal savedResult = BigDecimal.ZERO;

        BigDecimal result = userOperationService.getResultByOperationType(operandList,operationType,savedResult);

        assertThat(result).isEqualTo(new BigDecimal(-7));
    }

    @Test
    public void testGetResultByOperationTypeWhenOperationTypeIsMultiply(){
        List<BigDecimal> operandList = new ArrayList<BigDecimal>();
        operandList.add(new BigDecimal(2));
        operandList.add(new BigDecimal(5));
        OperatorType operationType = OperatorType.MULTIPLY;
        BigDecimal savedResult = BigDecimal.ZERO;

        BigDecimal result = userOperationService.getResultByOperationType(operandList,operationType,savedResult);

        assertThat(result).isEqualTo(new BigDecimal(10));
    }

    @Test
    public void testGetResultByOperationTypeWhenOperationTypeIsDivide(){
        List<BigDecimal> operandList = new ArrayList<BigDecimal>();
        operandList.add(new BigDecimal(10));
        operandList.add(new BigDecimal(5));
        OperatorType operationType = OperatorType.DIVIDE;
        BigDecimal savedResult = BigDecimal.ZERO;

        BigDecimal result = userOperationService.getResultByOperationType(operandList,operationType,savedResult);

        assertThat(result).isEqualTo(new BigDecimal(2));
    }

    @Test
    public void testGetResultByOperationTypeWhenOperationTypeIsEmpowerment(){
        List<BigDecimal> operandList = new ArrayList<BigDecimal>();
        operandList.add(new BigDecimal(2));
        operandList.add(new BigDecimal(2));
        OperatorType operationType = OperatorType.EMPOWERMENT;
        BigDecimal savedResult = BigDecimal.ZERO;

        BigDecimal result = userOperationService.getResultByOperationType(operandList,operationType,savedResult);

        assertThat(result).isEqualTo(new BigDecimal(4));
    }
}