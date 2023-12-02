package bg.softuni.marketplace.testutils;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.domain.FileEntity;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.enums.CategoriesEnum;
import bg.softuni.marketplace.repository.CategoryRepository;
import bg.softuni.marketplace.repository.FileRepository;
import bg.softuni.marketplace.repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TestDataUtil {

    private ItemRepository itemRepository;
    private FileRepository fileRepository;
    private CategoryRepository categoryRepository;

    public TestDataUtil(ItemRepository itemRepository, FileRepository fileRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.fileRepository = fileRepository;
        this.categoryRepository = categoryRepository;
    }


    public CategoryEntity createCategory(CategoriesEnum categoriesEnum){
        return this.categoryRepository.save(new CategoryEntity()
                .setType(categoriesEnum)
                .setDescription("Test category"));
    }


    public ItemEntity createItemTest(UserEntity owner) {

        FileEntity image = new FileEntity();
       fileRepository.save(image);



        ItemEntity testItem = new ItemEntity()
                .setName("TestItem")
                .setImage(image)
                .setPrice(BigDecimal.valueOf(200))
                .setDescription("Test description")
                .setUploadedDate(LocalDate.now())
                .setSeller(owner);

        ItemEntity save = itemRepository.save(testItem);

        return save;
    }
    public void cleanUp() {
        this.itemRepository.deleteAll();
        this.categoryRepository.deleteAll();
        this.fileRepository.deleteAll();
    }

}
