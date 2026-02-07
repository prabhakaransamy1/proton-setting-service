package com.yenmin.proton.setting.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * Validator class for custom validation annotation known as NullOrNotBlank to validate only blank
 * space.
 */
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

    public void initialize(NullOrNotBlank parameters) {
      
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || value.trim().length() > 0;
    }
}
