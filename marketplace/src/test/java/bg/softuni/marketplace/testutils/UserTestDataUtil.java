package bg.softuni.marketplace.testutils;

import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.repository.RoleRepository;
import bg.softuni.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserEntity createTestUser(String username){
        return createUser(username ,List.of(RolesEnum.USER));
    }

    public UserEntity createTestAdmin(String username){
        return createUser(username ,List.of(RolesEnum.ADMIN));
    }

    private UserEntity createUser(String username, List<RolesEnum> roles){
        var roleEntities = roleRepository.findAllByNameIn(roles);
        UserEntity newUser = new UserEntity()
                .setUsername(username)
                .setPassword("test")
                .setEmail("test@mail")
                .setConfirmedEmail(true)
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoleEntities(roleEntities);

        return userRepository.save(newUser);
    }

    public void cleanUp(){
        userRepository.deleteAll();
    }
}
