package bg.softuni.securitydemo.service;

import bg.softuni.securitydemo.model.dto.UserRegistrationDto;
import bg.softuni.securitydemo.model.entity.UserEntity;
import bg.softuni.securitydemo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public void registerUser(UserRegistrationDto userRegistrationDto,
                             Consumer<Authentication> successfulLoginProcessor){
        UserEntity user = new UserEntity()
                .setFirstName(userRegistrationDto.getFirstName())
                .setLastName(userRegistrationDto.getLastName())
                .setEmail(userRegistrationDto.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

       userRepository.save(user);

       UserDetails userDetails = userDetailsService.loadUserByUsername(userRegistrationDto.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);


    }
}
