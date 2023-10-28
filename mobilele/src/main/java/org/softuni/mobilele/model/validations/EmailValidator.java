package org.softuni.mobilele.model.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.softuni.mobilele.repository.UserRepository;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    private UserRepository userRepository;

    public EmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository.findByEmail(value).isEmpty();
    }
}
