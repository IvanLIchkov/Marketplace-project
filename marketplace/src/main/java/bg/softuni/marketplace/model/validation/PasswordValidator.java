package bg.softuni.marketplace.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class PasswordValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String first;
    private String second;
    private String message;


    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object firstValue = beanWrapper.getPropertyValue(this.first);
        Object secondValue = beanWrapper.getPropertyValue(this.second);

        boolean valid;

        if (firstValue == null){
            valid = secondValue == null;
        }else{
            valid = firstValue.equals(secondValue);
        }

        if(!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(first)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
