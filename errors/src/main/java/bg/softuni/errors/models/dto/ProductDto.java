package bg.softuni.errors.models.dto;

public class ProductDto {
    private String name;

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }
}
