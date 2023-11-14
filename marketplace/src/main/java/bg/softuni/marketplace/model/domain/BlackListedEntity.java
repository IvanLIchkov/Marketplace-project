package bg.softuni.marketplace.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "banned_users")
public class BlackListedEntity extends BaseEntity{

    @ManyToOne
    private UserEntity user;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String ipAddress;

    public UserEntity getUser() {
        return user;
    }

    public BlackListedEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public BlackListedEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public BlackListedEntity setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }
}
