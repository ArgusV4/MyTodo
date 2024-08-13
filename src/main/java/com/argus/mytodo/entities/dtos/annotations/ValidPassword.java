package com.argus.mytodo.entities.dtos.annotations;

import com.argus.mytodo.entities.dtos.validators.ValidPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidPasswordValidator.class)
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPassword {
    String message() default "The password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one numeric digit." +
            "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
