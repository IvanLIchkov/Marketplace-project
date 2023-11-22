package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.dto.UserViewDto;
import bg.softuni.marketplace.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/details/{id}")
    private String userDetails(@PathVariable Long id, Model model){
        UserViewDto userViewDto = this.userService.userView(id);
        model.addAttribute("userInfo", userViewDto);
        return "user-view";
    }
}
