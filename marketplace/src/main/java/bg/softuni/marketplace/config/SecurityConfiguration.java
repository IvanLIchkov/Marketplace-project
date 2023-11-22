package bg.softuni.marketplace.config;

import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.repository.UserRepository;
import bg.softuni.marketplace.service.impl.ApplicationUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.io.IOException;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http.
                // defines which pages will be authorized
                        authorizeHttpRequests().
                // allow access to all static files (images, CSS, js)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/users/login**").permitAll().
                requestMatchers("/users/login-error**").permitAll().
                // the URL-s below are available for all users - logged in and anonymous
                        requestMatchers("/").permitAll().
                        requestMatchers("/", "/users/register", "/user/activate/{code}").anonymous().
                requestMatchers("/admin").hasRole(RolesEnum.ADMIN.name()).
                // only for moderators
                anyRequest().authenticated().
                and().
                // configure login with HTML form
                        formLogin(
                                formLogin ->{
                                    formLogin
                                            .loginPage("/users/login")
                                            .usernameParameter("username")
                                            .passwordParameter("password")
                                            .defaultSuccessUrl("/", true)
                                            .failureHandler(handleAuthenticationFailure());
                                }
                )
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().securityContext().securityContextRepository(securityContextRepository);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler handleAuthenticationFailure(){
        return new SimpleUrlAuthenticationFailureHandler(){

            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

                if(exception.getClass().equals( BadCredentialsException.class)){
                    getRedirectStrategy().sendRedirect(request, response, "/users/login-error");
                }else{
                    getRedirectStrategy().sendRedirect(request,response, "/users/login-unactivated");
                }

            }

        };
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new ApplicationUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository());
    };
}
