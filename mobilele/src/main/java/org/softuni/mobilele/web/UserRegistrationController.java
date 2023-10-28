package org.softuni.mobilele.web;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.softuni.mobilele.model.dto.UserRegistrationDto;
import org.softuni.mobilele.service.UserService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {

  private final UserService userService;
  private final SecurityContextRepository securityContextRepository;

  public UserRegistrationController(UserService userService, SecurityContextRepository securityContextRepository) {
    this.userService = userService;
    this.securityContextRepository = securityContextRepository;
  }


  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("userRegistrationDto", new UserRegistrationDto());
    return "auth-register";
  }

  @PostMapping("/register")
  public String register( @Valid UserRegistrationDto userRegistrationDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

    if(bindingResult.hasErrors()){
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
      redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
      return "redirect:/users/register";
    }

    // TODO: Registration email with activation link

//    userService.registerUser(userRegistrationDTO, successFullAuth -> {
//      SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();
//
//      SecurityContext context = strategy.createEmptyContext();
//      context.setAuthentication(successFullAuth);
//
//      strategy.setContext(context);
//
//      securityContextRepository.saveContext(context, request, response);
//    });
    return "redirect:/";
  }


}
