package bg.softuni.marketplace.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activation_links")
public class UserActivationLinkEntity extends BaseEntity{

    private String activationLink;

    private Instant created;

    @ManyToOne
    private UserEntity user;

    public String getActivationLink() {
        return activationLink;
    }

    public UserActivationLinkEntity setActivationLink(String activationLink) {
        this.activationLink = activationLink;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public UserActivationLinkEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserActivationLinkEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
