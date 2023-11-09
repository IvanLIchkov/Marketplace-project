package bg.softuni.marketplace.model.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private TownEntity townEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roleEntities;

    @OneToMany(mappedBy = "saller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemEntity> itemsForSale;

//    @JoinColumn(name = "bought_items_id")
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemEntity> boughtItemEntities;

    @Column
    private String ipAddress;

    @Column
    private boolean confirmedEmail;

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public TownEntity getTownEntity() {
        return townEntity;
    }

    public UserEntity setTownEntity(TownEntity townEntity) {
        this.townEntity = townEntity;
        return this;
    }

    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public UserEntity setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
        return this;
    }

    public Set<ItemEntity> getItemsForSale() {
        return itemsForSale;
    }

    public UserEntity setItemsForSale(Set<ItemEntity> itemsForSale) {
        this.itemsForSale = itemsForSale;
        return this;
    }

    public Set<ItemEntity> getBoughtItemEntities() {
        return boughtItemEntities;
    }

    public UserEntity setBoughtItemEntities(Set<ItemEntity> boughtItemEntities) {
        this.boughtItemEntities = boughtItemEntities;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public UserEntity setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public boolean isConfirmedEmail() {
        return confirmedEmail;
    }

    public UserEntity setConfirmedEmail(boolean confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
        return this;
    }
}
