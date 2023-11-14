package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.UserRegisterDto;
import bg.softuni.marketplace.model.dto.UserViewForAdminPage;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.function.Consumer;

public interface UserService {



    void init();

    void registerUser(UserRegisterDto userRegisterDto, Consumer<Authentication> successfulLoginProcessor);

    UserEntity getUser();

    List<UserViewForAdminPage> adminPageViewUsers();

    UserViewForAdminPage userView(Long id);

    UserEntity findUserByEmail(String email);

    UserEntity findById(Long id);

    void promoteUser(Long aLong);
}
