package com.aos.repo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;

@NotBlank
@Size(min = 6, max = 32)
@Pattern(regexp = "^[A-Za-z0-9~`!@#\\$%\\^&\\*\\(\\)-_=\\+\\[\\]\\{\\}\\|]*$")
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordField {

  String message() default "{valid.fail}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
