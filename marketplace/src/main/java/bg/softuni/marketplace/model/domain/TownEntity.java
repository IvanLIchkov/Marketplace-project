package bg.softuni.marketplace.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "towns")
public class TownEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer population;

    public String getName() {
        return name;
    }

    public TownEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public TownEntity setPopulation(Integer population) {
        this.population = population;
        return this;
    }
}
