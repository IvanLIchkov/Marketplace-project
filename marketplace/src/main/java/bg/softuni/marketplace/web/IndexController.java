package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.AppUserDetails;
import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.service.CategoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class IndexController {

    private CategoryService categoryService;

    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    private List<CategoryEntity> categoryEntities(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal AppUserDetails appUserDetails, Model model){
        model.addAttribute("isLogged", appUserDetails != null);
        if(appUserDetails != null){
            model.addAttribute("username", appUserDetails.getUsername());
            model.addAttribute("categories", categoryService.getAllCategories());
        }
        return "index";
    }

}
