package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.enums.CategoriesEnum;
import bg.softuni.marketplace.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void init() {
        if (this.categoryRepository.count() ==0) {
            List<CategoryEntity> categoryInitList = Arrays.stream(CategoriesEnum.values())
                    .map(c -> {
                        if (c.equals(CategoriesEnum.HOME)) {
                            return new CategoryEntity().setType(c).setDescription("Things that every home!");
                        } else if (c.equals(CategoriesEnum.ELECTRONIC)) {
                            return new CategoryEntity().setType(c).setDescription("Electronics!");
                        } else if (c.equals(CategoriesEnum.SPORT)) {
                            return new CategoryEntity().setType(c).setDescription("Everything you need for sport!");
                        } else if (c.equals(CategoriesEnum.HOBBY)) {
                            return new CategoryEntity().setType(c).setDescription("All stuff you need for your hobby!");
                        } else {
                            return new CategoryEntity().setType(c).setDescription("Everything that is special item!");
                        }
                    }).toList();
            this.categoryRepository.saveAll(categoryInitList);
        }
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category is not found!"));
    }


}
