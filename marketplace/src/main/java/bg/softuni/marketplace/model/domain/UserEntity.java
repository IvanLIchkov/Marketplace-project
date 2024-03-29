package bg.softuni.marketplace.model.domain;

import jakarta.persistence.*;

import java.util.List;
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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roleEntities;

    @OneToMany(mappedBy = "seller")
    private Set<ItemEntity> itemsForSale;

    @OneToMany(mappedBy = "buyer")
    private Set<ItemEntity> boughtItemEntities;

    @Column
    private String ipAddress;

    @Column
    private boolean confirmedEmail;

    public void sellItem(ItemEntity itemEntity){
        this.itemsForSale.remove(itemEntity);
    }

    public void buyItem(ItemEntity itemEntity){
        this.boughtItemEntities.add(itemEntity);
    }

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

    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public UserEntity setRoleEntities(List<RoleEntity> roleEntities) {
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

    public UserEntity addRole(RoleEntity roleEntity){
        this.roleEntities.add(roleEntity);
        return this;
    }
}
