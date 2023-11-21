package bg.softuni.marketplace.repository;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.ItemDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findAllByCategory(CategoryEntity category);

    @Query("select new bg.softuni.marketplace.model.dto.ItemDetailsDto(i.id, i.pictureUrl, i.name, i.price, i.description, c.type, u.id, u.username) from ItemEntity i " +
            "join CategoryEntity c on i.category.id = c.id " +
            "join UserEntity u on i.seller.id = u.id " +
            "where i.id = :id")
    Optional<ItemDetailsDto> itemDetails(@Param("id") Long id);

}
