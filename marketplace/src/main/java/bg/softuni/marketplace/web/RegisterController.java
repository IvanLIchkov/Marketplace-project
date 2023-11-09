package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.domain.TownEntity;
import bg.softuni.marketplace.model.dto.UserRegisterDto;
import bg.softuni.marketplace.model.events.UserRegisteredEvent;
import bg.softuni.marketplace.service.TownService;
import bg.softuni.marketplace.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private UserService userService;
    private final SecurityContextRepository securityContextRepository;

    private final TownService townService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public RegisterController(UserService userService, SecurityContextRepository securityContextRepository, TownService townService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
        this.townService = townService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @ModelAttribute("userRegisterDto")
    public UserRegisterDto userRegisterDto(){
        return new UserRegisterDto();
    }

    @ModelAttribute("townList")
    private List<TownEntity> townList(){
        return townService.getAllTowns();
    }

    @GetMapping("/register")
    public String register(){
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid UserRegisterDto userRegisterDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request,
                                  HttpServletResponse response){


        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            return "redirect:/users/register";
        }
            userRegisterDto.setIpAddress(getIpAddressFromRequest(request));
        userService.registerUser(userRegisterDto, successFullAuth -> {
            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

            SecurityContext context = strategy.createEmptyContext();
            context.setAuthentication(successFullAuth);

            strategy.setContext(context);

            securityContextRepository.saveContext(context, request, response);
        });

        return "redirect:/";
    }
    private String getIpAddressFromRequest(HttpServletRequest request){
        String ipAddress= null;

        String xffHeader = request.getHeader("X-Forwarded-For");
        if (xffHeader != null && !xffHeader.startsWith("unknow")){
            int clientIdx = xffHeader.indexOf(",");
            if(clientIdx >0){
                ipAddress = xffHeader.substring(0, clientIdx - 1);
            }
        }
        if(ipAddress == null){
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
