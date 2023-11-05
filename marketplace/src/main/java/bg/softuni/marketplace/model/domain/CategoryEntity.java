package bg.softuni.marketplace.model.domain;

import bg.softuni.marketplace.model.enums.CategoriesEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriesEnum type;

    @Column(nullable = false)
    private String description;

    public CategoriesEnum getType() {
        return type;
    }

    public CategoryEntity setType(CategoriesEnum type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
