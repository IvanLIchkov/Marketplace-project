package bg.softuni.events.model;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private List<Long> allProductIds = new ArrayList<>();


    public List<Long> getAllProductIds() {
        return allProductIds;
    }

    public OrderDto setAllProductIds(List<Long> allProductIds) {
        this.allProductIds = allProductIds;
        return this;
    }

    public OrderDto addProduct(Long id) {
        this.allProductIds.add(id);
        return this;
    }
}
