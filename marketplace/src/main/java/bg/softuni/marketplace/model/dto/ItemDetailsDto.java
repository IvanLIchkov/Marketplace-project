package bg.softuni.marketplace.model.dto;

import bg.softuni.marketplace.model.domain.FileEntity;
import bg.softuni.marketplace.model.enums.CategoriesEnum;

import java.math.BigDecimal;

public class ItemDetailsDto {

    private long id;

    private Long imgId;

    private String name;

    private BigDecimal price;

    private String description;

    private CategoriesEnum categoryType;

    private Long sellerId;

    private String sellerUsername;

    private boolean isOwner;

    private boolean ownerForBuyButton;

    public ItemDetailsDto(long id,
                          Long imgId,
                          String name,
                          BigDecimal price,
                          String description,
                          CategoriesEnum categoryType,
                          Long sellerId,
                          String sellerUsername) {
        this.id = id;
        this.imgId = imgId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryType = categoryType;
        this.sellerId = sellerId;
        this.sellerUsername = sellerUsername;
    }

    public long getId() {
        return id;
    }

    public ItemDetailsDto setId(long id) {
        this.id = id;
        return this;
    }

    public Long getImgId() {
        return imgId;
    }

    public ItemDetailsDto setImgId(Long imgId) {
        this.imgId = imgId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemDetailsDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemDetailsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoriesEnum getCategoryType() {
        return categoryType;
    }

    public ItemDetailsDto setCategoryType(CategoriesEnum categoryType) {
        this.categoryType = categoryType;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public ItemDetailsDto setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public ItemDetailsDto setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public ItemDetailsDto setOwner(boolean owner) {
        isOwner = owner;
        return this;
    }

    public boolean isOwnerForBuyButton() {
        return ownerForBuyButton;
    }

    public ItemDetailsDto setOwnerForBuyButton(boolean ownerForBuyButton) {
        this.ownerForBuyButton = ownerForBuyButton;
        return this;
    }
}
