package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.BlackListedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackListedRepository extends JpaRepository<BlackListedEntity, Long> {

    Optional<BlackListedEntity> findByIpAddress(String ipAddress);
}
