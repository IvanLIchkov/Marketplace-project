package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.UserRegisterDto;
import bg.softuni.marketplace.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private final TownService townService;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, TownService townService, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.townService = townService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void init() {
        if(this.userRepository.count() == 0){
            this.userRepository.save(newAdmin());
            this.userRepository.save(newUser());
        }

    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto, Consumer<Authentication> successfulLoginProcessor) {
        UserEntity user = new UserEntity()
                .setUsername(userRegisterDto.getUsername())
                .setFirstName(userRegisterDto.getFirstName())
                .setLastName(userRegisterDto.getLastName())
                .setEmail(userRegisterDto.getEmail())
                .setRoleEntities(new HashSet<>())
                .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()))
                .setTownEntity(townService.findTownByName(userRegisterDto.getTownName()))
                .setIpAddress(userRegisterDto.getIpAddress());

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userRegisterDto.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        successfulLoginProcessor.accept(authentication);
    }

    @Override
    public UserEntity getUser() {
        return this.userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }


    private UserEntity newAdmin(){
        return new UserEntity()
                .setUsername("admin")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@example.com")
                .setPassword(passwordEncoder.encode("admin"))
                .setRoleEntities(this.roleService.findAllRoles());
    }

    private UserEntity newUser(){
        return new UserEntity()
                .setUsername("user")
                .setFirstName("User")
                .setLastName("userov")
                .setEmail("user@example.com")
                .setPassword(passwordEncoder.encode("user"));
    }
}
