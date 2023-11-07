package bg.softuni.marketplace.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class AddItemDto {

    @Size(min = 2, message = "Name size must be at least 2 symbols!")
    @NotEmpty(message = "Required field!")
    private String name;

    @NotNull(message = "Picture must not be empty!")
    private MultipartFile picture;

    private String pictureName;

    @NotNull(message = "Required field!")
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;

    @NotEmpty(message = "Required field!")
    @Size(min = 3, message = "Description size must be at least 3 symbols!")
    private String description;

    @NotNull(message = "Required field!")
    private Long categoryId;

    public String getName() {
        return name;
    }

    public AddItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public AddItemDto setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getPictureName() {
        return pictureName;
    }

    public AddItemDto setPictureName(String pictureName) {
        this.pictureName = pictureName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddItemDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public AddItemDto setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}
