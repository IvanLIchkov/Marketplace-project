package bg.softuni.marketplace.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordMatch {

    String first();

    String second();

    String message() default "Password doesn't match!";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
