package bg.softuni.marketplace.web;

import bg.softuni.marketplace.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/manage")
    private String manage(Model model){
        if(!model.containsAttribute("nonAdminUsers")){
            model.addAttribute("nonAdminUsers", this.userService.allNonAdminUsers());
        }
        return "manage-users-admin";
    }
}
