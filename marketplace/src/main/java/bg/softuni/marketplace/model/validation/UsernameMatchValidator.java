package bg.softuni.marketplace.model.validation;

import bg.softuni.marketplace.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameMatchValidator implements ConstraintValidator<UsernameMatch, String> {

    private UserRepository userRepository;

    public UsernameMatchValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean empty = this.userRepository.findByUsername(value).isEmpty();
        return empty;
    }
}
