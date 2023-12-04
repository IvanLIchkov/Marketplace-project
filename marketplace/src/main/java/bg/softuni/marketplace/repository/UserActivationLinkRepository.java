package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.UserActivationLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserActivationLinkRepository extends JpaRepository<UserActivationLinkEntity, Long> {
    Optional<UserActivationLinkEntity> findByActivationLink(String code);

    @Query("select l from UserActivationLinkEntity l " +
            "where l.created < :cutoffTime")
    List<UserActivationLinkEntity> findAllCretedBefor24Hours(@Param("cutoffTime") Instant cutoffTime);
}
