package com.ervingorospe.grab_user_service.validate.birthdate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinAge {
    String message() default "User must be at least 10 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
