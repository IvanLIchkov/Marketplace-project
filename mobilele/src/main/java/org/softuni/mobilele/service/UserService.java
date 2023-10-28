package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.UserRegistrationDto;
import org.springframework.security.core.Authentication;

import java.util.function.Consumer;

public interface UserService {

  void registerUser(UserRegistrationDto userRegistrationDTO, Consumer<Authentication> successfulLoginProcessor);


}
