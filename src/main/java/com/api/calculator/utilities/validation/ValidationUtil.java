package com.api.calculator.utilities.validation;

import com.api.calculator.exceptions.RequiredFieldsException;

import java.util.Arrays;

public class ValidationUtil {

    public static void validateRequiredFields(String... fieldList) {
        Arrays.stream(fieldList).forEach(field -> {
            if (field.isEmpty()) {
                throw new RequiredFieldsException();
            }
        });
    }
}
