package bg.softuni.securitydemo.service;

import bg.softuni.securitydemo.model.AppUserDetails;
import bg.softuni.securitydemo.model.entity.UserEntity;
import bg.softuni.securitydemo.model.entity.UserRoleEntity;
import bg.softuni.securitydemo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
                userRepository.findUserEntityByEmail(username)
                        .map(this::map)
                        .orElseThrow(() -> new UsernameNotFoundException("User with name "+ username +" not found!"));
    }

    private UserDetails map(UserEntity userEntity){
       return new AppUserDetails(
                userEntity.getEmail(),
                userEntity.getPassword(),
                extractAuthorities(userEntity))
               .setCountry(userEntity.getCountry()).setFullName(userEntity.getFirstName()+" " + userEntity.getLastName());
    }

    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity){
       return userEntity
                .getRoles()
                .stream()
                .map(this::mapRole)
                .toList();
    }

    private GrantedAuthority mapRole(UserRoleEntity userRoleEntity){
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
}
