package bg.softuni.marketplace.service;

import bg.softuni.marketplace.model.domain.CategoryEntity;

import java.util.List;

public interface CategoryService {


    List<CategoryEntity> getAllCategories();

    CategoryEntity getCategoryById(Long categoryId);
}
