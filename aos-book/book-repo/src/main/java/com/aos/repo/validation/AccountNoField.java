package com.aos.repo.validation;

import com.aos.core.validation.NotEndsWithSpace;
import com.aos.core.validation.NotStartsWithSpace;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;

@Size(max = 32)
@NotStartsWithSpace
@NotEndsWithSpace
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountNoField {

  String message() default "{valid.fail}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
