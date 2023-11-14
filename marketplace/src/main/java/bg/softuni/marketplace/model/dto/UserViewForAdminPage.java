package bg.softuni.marketplace.model.dto;

import bg.softuni.marketplace.model.domain.RoleEntity;

import java.util.List;
import java.util.Set;

public class UserViewForAdminPage {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String town;

    private Set<RoleEntity> roleEntities;


    public Long getId() {
        return id;
    }

    public UserViewForAdminPage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewForAdminPage setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewForAdminPage setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewForAdminPage setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewForAdminPage setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public UserViewForAdminPage setTown(String town) {
        this.town = town;
        return this;
    }


    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public UserViewForAdminPage setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
        return this;
    }
}
