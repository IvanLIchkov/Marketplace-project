package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.UserActivationLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserActivationLinkRepository extends JpaRepository<UserActivationLinkEntity, Long> {
    Optional<UserActivationLinkEntity> findByActivationLink(String code);

    List<UserActivationLinkEntity> findAllByCreatedGreaterThanEqual(Instant wednesday);
}
