package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.RoleEntity;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void init();

    Set<RoleEntity> findAllRoles();
}
