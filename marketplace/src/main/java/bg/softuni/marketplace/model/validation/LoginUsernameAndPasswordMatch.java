package bg.softuni.marketplace.model.validation;


import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class LoginUsernameAndPasswordMatch implements ConstraintValidator<LoginValidation, Object> {

    private String username;
    private String password;
    private String message;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUsernameAndPasswordMatch(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize(LoginValidation constraintAnnotation) {
        username = constraintAnnotation.first();
        password = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object username = beanWrapper.getPropertyValue(this.username);
        Object password = beanWrapper.getPropertyValue(this.password);
        boolean valid;

        if(username == null || password == null){
            valid = password == null;
        }else {
            Optional<UserEntity> user = this.userRepository.findByUsername(username.toString());
            valid = user.isPresent();
            if(valid){
                valid = passwordEncoder.matches(password.toString(), user.get().getPassword());
            }
        }
        if(!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(this.username)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(this.password)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

       return valid;
    }
}
