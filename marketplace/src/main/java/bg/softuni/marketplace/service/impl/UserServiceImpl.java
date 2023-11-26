package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.UserRegisterDto;
import bg.softuni.marketplace.model.dto.UserViewDto;
import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.model.events.UserRegisteredEvent;
import bg.softuni.marketplace.repository.UserRepository;
import bg.softuni.marketplace.service.RoleService;
import bg.softuni.marketplace.service.TownService;
import bg.softuni.marketplace.service.UserService;
import bg.softuni.marketplace.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private final TownService townService;
    private final UserDetailsService userDetailsService;
    private final ModelMapper mapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, TownService townService, UserDetailsService userDetailsService, ModelMapper mapper, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.townService = townService;
        this.userDetailsService = userDetailsService;
        this.mapper = mapper;
        this.applicationEventPublisher = applicationEventPublisher;
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
                .setRoleEntities(new ArrayList<>(this.roleService.findUserRoleEntity()))
                .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()))
                .setTownEntity(townService.findTownByName(userRegisterDto.getTownName()))
                .setIpAddress(userRegisterDto.getIpAddress())
                        .setConfirmedEmail(false);

        userRepository.save(user);
        applicationEventPublisher.publishEvent(
                new UserRegisteredEvent("UserService",
                        userRegisterDto.getEmail(),userRegisterDto.getUsername()));
    }

    @Override
    public UserEntity getUser() {
        return this.userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }

    @Override
    public List<UserViewDto> adminPageViewUsers() {
        List<UserViewDto> collect = this.userRepository.findAll()
                .stream()
                .map(u -> this.mapper.map(u, UserViewDto.class))
                .toList();
        return collect;
    }

    @Override
    public UserViewDto userView(Long id){
        return this.userRepository.findById(id).map(u -> this.mapper.map(u, UserViewDto.class)).orElseThrow(() -> new ObjectNotFoundException("No such user in database!"));
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public UserEntity findByUsername(String username){
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with that username doesn't exist in database!"));
    }

    @Override
    public void promoteUser(Long userId) {
        UserEntity userToPromote = this.userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userToPromote.addRole(this.roleService.findRoleByName(RolesEnum.ADMIN));
        this.userRepository.save(userToPromote);
    }


    private UserEntity newAdmin(){
        return new UserEntity()
                .setUsername("admin")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@example.com")
                .setPassword(passwordEncoder.encode("admin"))
                .setRoleEntities(this.roleService.findAllRoles())
                .setConfirmedEmail(true);
    }

    private UserEntity newUser(){
        return new UserEntity()
                .setUsername("user")
                .setFirstName("User")
                .setLastName("userov")
                .setEmail("user@example.com")
                .setPassword(passwordEncoder.encode("user"))
                .setRoleEntities(this.roleService.findUserRoleEntity())
                .setConfirmedEmail(true);
    }
}
