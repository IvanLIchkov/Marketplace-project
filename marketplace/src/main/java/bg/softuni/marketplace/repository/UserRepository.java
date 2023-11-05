package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String value);

    Optional<UserEntity> findByUsername(String string);
}
