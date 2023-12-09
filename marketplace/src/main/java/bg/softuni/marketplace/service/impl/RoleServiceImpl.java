package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.RoleEntity;
import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.repository.RoleRepository;
import bg.softuni.marketplace.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    @Override
    public List<RoleEntity> findAllRoles() {

       return new ArrayList<>(this.roleRepository.findAll());
    }

    @Override
    public List<RoleEntity> findUserRoleEntity(){
        return new ArrayList<>(this.roleRepository.findByName(RolesEnum.USER));
    }

    @Override
    public RoleEntity findRoleByName(RolesEnum rolesEnum){
        return this.roleRepository.findRoleEntitiesByName(rolesEnum);
    }
}
