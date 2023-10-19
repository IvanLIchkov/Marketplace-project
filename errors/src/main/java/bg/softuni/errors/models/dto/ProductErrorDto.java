package bg.softuni.errors.models.dto;

public class ProductErrorDto {

    private long productId;

    private String description;


    public ProductErrorDto(long productId, String description) {
        this.productId = productId;
        this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public ProductErrorDto setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductErrorDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
