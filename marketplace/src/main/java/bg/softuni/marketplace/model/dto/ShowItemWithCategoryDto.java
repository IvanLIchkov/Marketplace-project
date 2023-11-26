package bg.softuni.marketplace.model.dto;

import java.math.BigDecimal;

public class ShowItemWithCategoryDto {
    private Long id;

    private Long imgId;

    private String name;

    private BigDecimal price;

   private String categoryName;

    public Long getId() {
        return id;
    }

    public ShowItemWithCategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getImgId() {
        return imgId;
    }

    public ShowItemWithCategoryDto setImgId(Long imgId) {
        this.imgId = imgId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShowItemWithCategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShowItemWithCategoryDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ShowItemWithCategoryDto setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
