package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.RoleEntity;
import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void init() {
        if(this.roleRepository.count() ==0){
            List<RoleEntity> roleInitList = Arrays.stream(RolesEnum.values())
                    .map(r -> {
                        return new RoleEntity().setName(r);
                    }).toList();

            this.roleRepository.saveAll(roleInitList);
        }
    }

    @Override
    public Set<RoleEntity> findAllRoles() {

       return new HashSet<>(this.roleRepository.findAll());
    }
}
