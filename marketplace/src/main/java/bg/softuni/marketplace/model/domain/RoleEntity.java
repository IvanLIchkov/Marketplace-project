package bg.softuni.marketplace.model.domain;

import bg.softuni.marketplace.model.enums.RolesEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    @ManyToMany(mappedBy = "roleEntities")
    private Set<UserEntity> users;


    public RolesEnum getName() {
        return name;
    }

    public RoleEntity setName(RolesEnum name) {
        this.name = name;
        return this;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public RoleEntity setUsers(Set<UserEntity> users) {
        this.users = users;
        return this;
    }
}
