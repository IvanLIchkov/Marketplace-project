package bg.softuni.marketplace.testutils;

import bg.softuni.marketplace.model.domain.RoleEntity;
import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new RoleEntity().setName(RolesEnum.USER),
                    new RoleEntity().setName(RolesEnum.ADMIN)
            ));
        }
    }
}
