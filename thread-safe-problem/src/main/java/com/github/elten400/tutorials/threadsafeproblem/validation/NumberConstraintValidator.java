package com.github.elten400.tutorials.threadsafeproblem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberConstraintValidator implements ConstraintValidator<NumberConstraint, Integer> {
    private Integer number;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        this.number = value;
        return checkNumber();
    }

    private boolean checkNumber() {
        return number > 30;
    }
}
