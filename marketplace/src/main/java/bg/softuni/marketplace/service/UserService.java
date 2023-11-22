package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.UserRegisterDto;
import bg.softuni.marketplace.model.dto.UserViewDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.function.Consumer;

public interface UserService {



    void init();

    void registerUser(UserRegisterDto userRegisterDto, Consumer<Authentication> successfulLoginProcessor);

    UserEntity getUser();

    List<UserViewDto> adminPageViewUsers();

    UserViewDto userView(Long id);

    UserEntity findUserByEmail(String email);

    UserEntity findById(Long id);

    UserEntity findByUsername(String username);

    void promoteUser(Long aLong);
}
