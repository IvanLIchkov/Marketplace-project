package bg.softuni.marketplace.model.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @OneToOne
    private FileEntity image;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate uploadedDate;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private UserEntity seller;

    @ManyToOne
    private UserEntity buyer;

    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public FileEntity getImage() {
        return image;
    }

    public ItemEntity setImage(FileEntity image) {
        this.image = image;
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

    public UserEntity getBuyer() {
        return buyer;
    }

    public ItemEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }
}
