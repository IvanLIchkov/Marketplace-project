package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.dto.AddItemDto;
import bg.softuni.marketplace.model.enums.CategoriesEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {

    void addItem(AddItemDto addItemDto, String fileName, MultipartFile multipartFile) throws IOException;

    List<ItemEntity> allItemsByType(Long categoryId);
}
