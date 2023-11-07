package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.dto.AddItemDto;
import bg.softuni.marketplace.repository.ItemRepository;
import bg.softuni.marketplace.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private CategoryService categoryService;
    private UserService userService;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, UserService userService) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void addItem(AddItemDto addItemDto, String fileName, MultipartFile multipartFile) throws IOException {
        ItemEntity persistedItem = this.itemRepository.save(itemMap(addItemDto));

        String upload = "src/main/resources/static/images/usersPictures/" ;

        FileUploadUtil.saveFile(upload, fileName, multipartFile);

    }

    @Override
    public List<ItemEntity> allItemsByType(Long categoryId) {

        CategoryEntity categoryById = this.categoryService.getCategoryById(categoryId);

        return this.itemRepository.findAllByCategory(categoryById);
    }

    private ItemEntity itemMap(AddItemDto addItemDto){
        return new ItemEntity()
                .setName(addItemDto.getName())
                .setPictureUrl(addItemDto.getPictureName())
                .setPrice(addItemDto.getPrice())
                .setDescription(addItemDto.getDescription())
                .setUploadedDate(LocalDate.now())
                .setCategory(categoryService.getCategoryById(addItemDto.getCategoryId()))
                .setSaller(this.userService.getUser());
    }
}
