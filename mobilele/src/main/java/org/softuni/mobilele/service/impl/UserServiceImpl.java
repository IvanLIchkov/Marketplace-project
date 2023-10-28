package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.UserRegistrationDto;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;

  public UserServiceImpl(
          UserRepository userRepository,
          PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public void registerUser(
          UserRegistrationDto userRegistrationDTO,
          Consumer<Authentication> successfulLoginProcessor) {

    userRepository.save(map(userRegistrationDTO));

    UserDetails userDetails = userDetailsService.loadUserByUsername(userRegistrationDTO.getEmail());

    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
  }


  private UserEntity map(UserRegistrationDto userRegistrationDTO) {
    return new UserEntity()
        .setActive(true)
        .setFirstName(userRegistrationDTO.getFirstName())
        .setLastName(userRegistrationDTO.getLastName())
        .setEmail(userRegistrationDTO.getEmail())
        .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
  }
}
