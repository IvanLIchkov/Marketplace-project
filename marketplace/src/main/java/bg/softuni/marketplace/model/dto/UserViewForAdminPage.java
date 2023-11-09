package bg.softuni.marketplace.model.dto;

import bg.softuni.marketplace.model.domain.RoleEntity;

import java.util.List;
import java.util.Set;

public class UserViewForAdminPage {

    private Long id;

    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    public UserViewForAdminPage setFullName(String fullName) {
        this.fullName = fullName;
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



}
