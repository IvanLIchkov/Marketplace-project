package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.*;
import bg.softuni.marketplace.model.dto.AddItemDto;
import bg.softuni.marketplace.model.dto.ItemDetailsDto;
import bg.softuni.marketplace.model.dto.ShowItemDto;
import bg.softuni.marketplace.model.dto.ShowItemWithCategoryDto;
import bg.softuni.marketplace.model.enums.RolesEnum;
import bg.softuni.marketplace.repository.ItemRepository;
import bg.softuni.marketplace.repository.UserRepository;
import bg.softuni.marketplace.service.CategoryService;
import bg.softuni.marketplace.service.FileService;
import bg.softuni.marketplace.service.ItemService;
import bg.softuni.marketplace.service.UserService;
import bg.softuni.marketplace.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private final UserRepository userRepository;
    private CategoryService categoryService;
    private UserService userService;
    private final FileService fileService;
    private final ModelMapper mapper;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository, CategoryService categoryService, UserService userService, FileService fileService, ModelMapper mapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.fileService = fileService;
        this.mapper = mapper;
    }

    @Override
    public void addItem(AddItemDto addItemDto, UserDetails seller) throws IOException {
        FileEntity upload = this.fileService.upload(addItemDto.getImg());
        this.itemRepository.save(itemMap(addItemDto, seller, upload));

    }

    @Override
    @Transactional
    public void deleteOffer(Long id) {
        this.itemRepository.deleteById(id);
    }

    @Override
    public void buyItem(Long itemId, String username) {
        ItemEntity itemToBuy = this.itemRepository.findById(itemId).orElseThrow(()-> new ObjectNotFoundException("Item is not found!"));
        UserEntity seller = itemToBuy.getSeller();
        UserEntity buyer = this.userService.findByUsername(username);
        buyer.buyItem(itemToBuy);
        seller.sellItem(itemToBuy);
        this.userRepository.saveAll(List.of(buyer, seller));
    }




    @Override
    public List<ShowItemDto> allItems(){
        return this.itemRepository.findAllByBuyerIsNull()
                .stream()
                .map(i -> this.mapper.map(i, ShowItemDto.class).setImgId(i.getImage().getId()))
                .toList();

    }

    @Override
    public ItemDetailsDto itemDetailsById(Long id, UserDetails viewer){
        ItemDetailsDto itemDetailsDto = this.itemRepository
                .itemDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("Item is not found in database!"));
        itemDetailsDto.setOwner(isOwner(itemDetailsDto.getId(), viewer));
        itemDetailsDto.setOwnerForBuyButton(isOwnerForBuyButton(itemDetailsDto.getId(), viewer));
        return itemDetailsDto;
    }

    @Override
    public List<ShowItemWithCategoryDto> allItemsByType(Long categoryId) {

        CategoryEntity categoryById = this.categoryService.getCategoryById(categoryId);
        return this.itemRepository.findAllByCategoryAndBuyerIsNull(categoryById).stream()
                .map(i->this.mapper.map(i, ShowItemWithCategoryDto.class)
                        .setImgId(i.getImage().getId())
                        .setCategoryName(i.getCategory().getType().name()))
                .toList();
    }




    @Override
    public boolean isOwnerForBuyButton(Long itemId, UserDetails viewer){
        if(viewer == null){
            return false;
        }
        UserEntity viewerEntity = this.userService.findByUsername(viewer.getUsername());
        return Objects.equals(this.itemRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Element doesn't contain.")).getSeller().getId(), viewerEntity.getId());
    }

    @Override
    public boolean isOwner(Long itemId, UserDetails viewer){
        if(viewer == null){
            return false;
        }
        UserEntity viewerEntity = this.userService.findByUsername(viewer.getUsername());
        if (isAdmin(viewerEntity)) {
            return true;
        }

        return Objects.equals(this.itemRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Element doesn't contain.")).getSeller().getId(), viewerEntity.getId());
    }




    private boolean isAdmin(UserEntity user){
        return user.getRoleEntities().stream()
                .map(RoleEntity::getName)
                .anyMatch(r -> RolesEnum.ADMIN == r);
    }

    private ItemEntity itemMap(AddItemDto addItemDto, UserDetails seller, FileEntity upload){
        return new ItemEntity()
                .setName(addItemDto.getName())
                .setImage(upload)
                .setPrice(addItemDto.getPrice())
                .setDescription(addItemDto.getDescription())
                .setUploadedDate(LocalDate.now())
                .setCategory(categoryService.getCategoryById(addItemDto.getCategoryId()))
                .setSeller(this.userService.findByUsername(seller.getUsername()));
    }
}
