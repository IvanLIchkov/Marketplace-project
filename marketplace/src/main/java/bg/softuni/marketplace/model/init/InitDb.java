package bg.softuni.marketplace.model.init;

import bg.softuni.marketplace.service.CategoryService;
import bg.softuni.marketplace.service.RoleService;
import bg.softuni.marketplace.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDb implements CommandLineRunner {

    private CategoryService categoryService;
    private RoleService roleService;
    private UserService userService;

    private InitDb(CategoryService categoryService, RoleService roleService, UserService userService) {
        this.categoryService = categoryService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.init();
        roleService.init();
        userService.init();
    }


}
