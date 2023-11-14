package bg.softuni.marketplace.web;

import bg.softuni.marketplace.service.UserActivationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ActivationLinkController {

    private final UserActivationService userActivationService;

    public ActivationLinkController(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @GetMapping("/activate/{code}")
    private String activateLink(@PathVariable String code){
        userActivationService.activateUser(code);

        return "confirmed-email";
    }
}
