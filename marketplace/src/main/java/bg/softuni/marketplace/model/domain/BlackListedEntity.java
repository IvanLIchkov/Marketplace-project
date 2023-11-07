package bg.softuni.marketplace.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "banned_users")
public class BlackListedEntity extends BaseEntity{

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String ipAddress;

    public String getUserId() {
        return userId;
    }

    public BlackListedEntity setUserId(String userId) {
        this.userId = userId;
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
