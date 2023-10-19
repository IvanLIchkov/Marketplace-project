package bg.softuni.securitydemo.service;

import bg.softuni.securitydemo.model.entity.UserEntity;
import bg.softuni.securitydemo.model.entity.UserRoleEntity;
import bg.softuni.securitydemo.model.enums.UserRoleEnum;
import bg.softuni.securitydemo.repository.UserRepository;
import bg.softuni.securitydemo.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${app.default.password}") String defaultPassword){

        this.userRoleRepository = userRoleRepository;

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @PostConstruct
    public void init(){
        initRoles();
        initUsers();

    }

    private void initRoles(){
        if(userRoleRepository.count() == 0){
            var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
        }
    }

    private void initUsers(){
        if(userRepository.count() == 0){
            initAdmin();
            initModerator();
            initNormalUser();
        }
    }

    private void initAdmin(){
        var adminUser = new UserEntity()
                .setEmail("admin@example.com")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setCountry("Bulgaria")
                .setPassword(passwordEncoder.encode(defaultPassword))
                .setRoles(userRoleRepository.findAll());

        userRepository.save(adminUser);
    }

    private void initModerator(){

        var moderatorUser = new UserEntity()
                .setEmail("moderator@example.com")
                .setFirstName("Moderator")
                .setLastName("Moderatorov")
                .setCountry("Greece")
                .setPassword(passwordEncoder.encode(defaultPassword))
                .setRoles(List.of(userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow()));

        userRepository.save(moderatorUser);
    }
    private void initNormalUser(){

        var normalUser = new UserEntity()
                .setEmail("user@example.com")
                .setFirstName("User")
                .setLastName("Userov")
                .setCountry("Tanzania")
                .setPassword(passwordEncoder.encode(defaultPassword));

        userRepository.save(normalUser);
    }
}