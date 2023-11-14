package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.RoleEntity;
import bg.softuni.marketplace.model.enums.RolesEnum;

import java.util.List;

public interface RoleService {
    void init();

    List<RoleEntity> findAllRoles();

    List<RoleEntity> findUserRoleEntity();

    RoleEntity findRoleByName(RolesEnum rolesEnum);
}
