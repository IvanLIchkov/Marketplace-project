package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.AddItemDto;
import bg.softuni.marketplace.model.dto.ItemDetailsDto;
import bg.softuni.marketplace.model.dto.ShowItemDto;
import bg.softuni.marketplace.repository.ItemRepository;
import bg.softuni.marketplace.service.CategoryService;
import bg.softuni.marketplace.service.ItemService;
import bg.softuni.marketplace.service.UserService;
import bg.softuni.marketplace.util.FileUploadUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private CategoryService categoryService;
    private UserService userService;
    private final ModelMapper mapper;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, UserService userService, ModelMapper mapper) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public void addItem(AddItemDto addItemDto, String fileName, MultipartFile multipartFile) throws IOException {
        ItemEntity persistedItem = this.itemRepository.save(itemMap(addItemDto));

        String upload = "marketplace/src/main/resources/static/images/usersPictures" ;

        FileUploadUtil.saveFile(upload, fileName, multipartFile);

    }

    @Override
    public ItemDetailsDto itemDetailsById(String id){
        ItemDetailsDto itemDetailsDto = this.itemRepository.itemDetails(Long.valueOf(id)).orElseThrow(() -> new NoSuchElementException("Item is not found in database!"));
        return itemDetailsDto;
    }

    @Override
    public List<ShowItemDto> allItems(){
        return this.itemRepository.findAll()
                .stream()
                .map(i -> this.mapper.map(i, ShowItemDto.class))
                .toList();

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
                .setSeller(this.userService.getUser());
    }
}
