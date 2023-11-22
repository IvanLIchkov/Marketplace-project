package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.RoleEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.enums.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Set<RoleEntity> findByName(RolesEnum rolesEnum);

    RoleEntity findRoleEntitiesByName(RolesEnum rolesEnum);

    List<RoleEntity> findAllByNameIn(List<RolesEnum> roles);
}
