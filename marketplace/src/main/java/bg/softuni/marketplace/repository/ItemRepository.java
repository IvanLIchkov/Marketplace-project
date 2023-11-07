package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findAllByCategory(CategoryEntity category);
}
