package bg.softuni.marketplace.web;

import bg.softuni.marketplace.service.RoleService;
import bg.softuni.marketplace.service.UserService;
import bg.softuni.marketplace.service.impl.BlackListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final BlackListService blackListService;

    public AdminController(UserService userService, RoleService roleService, BlackListService blackListService) {
        this.userService = userService;
        this.blackListService = blackListService;
    }

    @GetMapping("/manage")
    private String manage(Model model){
        if(!model.containsAttribute("nonAdminUsers")){
            model.addAttribute("nonAdminUsers", this.userService.adminPageViewUsers());
        }
        return "manage-users-admin";
    }

    @GetMapping("/ban/{userId}")
    public String banUser(@PathVariable Long userId){
        this.blackListService.banUser(userService.findById(userId));
        return "redirect:/admin/manage";
    }
    @GetMapping("/unban/{userId}")
    public String unbanUser(@PathVariable Long userId){
        this.blackListService.unbanUser(userId);
        return "redirect:/admin/manage";
    }

    @GetMapping("/promote/{userId}")
    public String promoteUserToAdmin(@PathVariable Long userId){
        this.userService.promoteUser(userId);
        return "redirect:/admin/manage";
    }

    @GetMapping("/view/details/{userId}")
    public String getUserDetailsView(@PathVariable String userId, Model model){
        model.addAttribute("userInfo", this.userService.userView(Long.valueOf(userId)));
        return "user-view";
    }
}
