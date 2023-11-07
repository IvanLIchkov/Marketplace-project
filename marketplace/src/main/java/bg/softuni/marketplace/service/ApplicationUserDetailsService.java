package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.AppUserDetails;
import bg.softuni.marketplace.model.domain.RoleEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with name "+username+" not found!"));
    }
    private UserDetails map(UserEntity userEntity){
        return new AppUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword(),
                extractAuthorities(userEntity));
    }

    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity){
        return userEntity
                .getRoleEntities()
                .stream()
                .map(this::mapRole)
                .toList();
    }

    private GrantedAuthority mapRole(RoleEntity roleEntity){
        return new SimpleGrantedAuthority("ROLE_" + roleEntity.getName().name());
    }
}
