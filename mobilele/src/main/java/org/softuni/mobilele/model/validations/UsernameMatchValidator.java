package org.softuni.mobilele.model.validations;

import org.softuni.mobilele.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameMatchValidator implements ConstraintValidator<UsernameMatch, String> {

    private UserRepository userRepository;

    public UsernameMatchValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository.findByEmail(value).isEmpty();
    }
}
