package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
