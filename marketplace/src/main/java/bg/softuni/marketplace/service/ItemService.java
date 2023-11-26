package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.dto.AddItemDto;
import bg.softuni.marketplace.model.dto.ItemDetailsDto;
import bg.softuni.marketplace.model.dto.ShowItemDto;
import bg.softuni.marketplace.model.dto.ShowItemWithCategoryDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;

public interface ItemService {


    void addItem(AddItemDto addItemDto, UserDetails seller) throws IOException;

    ItemDetailsDto itemDetailsById(String id, UserDetails viewer);

    boolean isOwnerForBuyButton(Long itemId, UserDetails viewer);

    boolean isOwner(Long itemId, UserDetails viewer);

    List<ShowItemDto> allItems();

    List<ShowItemWithCategoryDto> allItemsByType(Long categoryId);

    void deleteOffer(Long id);


    void buyItem(Long itemId, String username);
}
