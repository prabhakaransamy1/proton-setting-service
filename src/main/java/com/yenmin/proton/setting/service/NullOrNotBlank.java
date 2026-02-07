package com.yenmin.proton.setting.service;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom validation annotation known as NullOrNotBlank to validate only blank
 * space.
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
public @interface NullOrNotBlank {
	String message() default "{javax.validation.constraints.Pattern.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}