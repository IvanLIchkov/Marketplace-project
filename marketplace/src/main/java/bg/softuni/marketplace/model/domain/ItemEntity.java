package bg.softuni.marketplace.model.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(length = 64)
    private String pictureUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate uploadedDate;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private UserEntity seller;


    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public ItemEntity setPictureUrl(String picture) {
        this.pictureUrl = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getUploadedDate() {
        return uploadedDate;
    }

    public ItemEntity setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ItemEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public ItemEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

}
