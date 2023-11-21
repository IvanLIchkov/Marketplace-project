package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.RoleEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private ApplicationUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp(){
        serviceToTest = new ApplicationUserDetailsService(
        mockUserRepository
        );
    }

//    @Test  TODO: Example of some testing
//    void testMock(){
//
//        UserEntity userEntity = new UserEntity().setFirstName("Anna");
//
//        when(mockUserRepository.findByEmail("test@example.com"))
//                .thenReturn(Optional.of(userEntity));
//
//        Optional<UserEntity> optionalUser = mockUserRepository.findByEmail("test@example.com");
//
//
//
//        Assertions.assertEquals(optionalUser.get(), userEntity);
//    }

    @Test
    void testUserNotFound(){
        Assertions.assertThrows(UsernameNotFoundException.class, () ->serviceToTest.loadUserByUsername("pesho@test.com"));
    }

    @Test
    void testUserFoundException(){
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUserEntity.getUsername(), userDetails.getUsername(), "Username is not populated properly.");
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + RolesEnum.ADMIN), "The user is not admin");
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + RolesEnum.USER), "The user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority){
        return userDetails.getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEntity createTestUser(){
        return new UserEntity()
                .setUsername("testUsername")
                .setFirstName("firstNameTest")
                .setLastName("lastNameTest")
                .setEmail("email@test.com")
                .setPassword("topSecret")
                .setConfirmedEmail(false)
                .setRoleEntities(List.of(
                        new RoleEntity().setName(RolesEnum.ADMIN),
                        new RoleEntity().setName(RolesEnum.USER)
                ));
    }
}
