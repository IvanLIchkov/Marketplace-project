package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.UserDetailsView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    private String index(@AuthenticationPrincipal UserDetailsView userDetailsView, Model model){
        model.addAttribute("isLogged", userDetailsView != null);
        if(userDetailsView != null){
            model.addAttribute("username", userDetailsView.getUsername());
        }

        return "index";
    }
}
