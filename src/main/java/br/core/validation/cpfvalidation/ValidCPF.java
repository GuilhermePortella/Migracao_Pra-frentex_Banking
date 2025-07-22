package br.core.validation.cpfvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorCPF.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCPF {
   String message() default "CPF inválido";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};

}
