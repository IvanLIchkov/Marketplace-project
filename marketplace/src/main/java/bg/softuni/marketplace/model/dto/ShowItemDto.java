package bg.softuni.marketplace.model.dto;

import java.math.BigDecimal;

public class ShowItemDto {

    private Long id;

    private Long imgId;

    private String name;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public ShowItemDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getImgId() {
        return imgId;
    }

    public ShowItemDto setImgId(Long imgId) {
        this.imgId = imgId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShowItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShowItemDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
