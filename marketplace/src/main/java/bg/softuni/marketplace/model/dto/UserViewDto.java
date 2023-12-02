package bg.softuni.marketplace.model.dto;

import bg.softuni.marketplace.model.domain.RoleEntity;

import java.util.Set;

public class UserViewDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String town;

    private Set<RoleEntity> roleEntities;

    private boolean isBanned;


    public Long getId() {
        return id;
    }

    public UserViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public UserViewDto setTown(String town) {
        this.town = town;
        return this;
    }


    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public UserViewDto setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
        return this;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public UserViewDto setBanned(boolean banned) {
        isBanned = banned;
        return this;
    }
}
