package bg.softuni.marketplace.model.init;

import bg.softuni.marketplace.service.CategoryService;
import bg.softuni.marketplace.service.RoleService;
import bg.softuni.marketplace.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDb implements CommandLineRunner {
    private UserService userService;

    private InitDb( UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.init();
    }


}
