package bg.softuni.marketplace.service.impl;

import bg.softuni.marketplace.model.domain.CategoryEntity;
import bg.softuni.marketplace.model.enums.CategoriesEnum;
import bg.softuni.marketplace.repository.CategoryRepository;
import bg.softuni.marketplace.service.CategoryService;
import bg.softuni.marketplace.service.exceptions.ObjectNotFoundException;
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
    public List<CategoryEntity> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(() -> new ObjectNotFoundException("Category is not found!"));
    }


}
