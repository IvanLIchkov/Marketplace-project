package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.UserRegisterDto;
import org.springframework.security.core.Authentication;

import java.util.function.Consumer;

public interface UserService {



    void init();

    void registerUser(UserRegisterDto userRegisterDto, Consumer<Authentication> successfulLoginProcessor);
}
