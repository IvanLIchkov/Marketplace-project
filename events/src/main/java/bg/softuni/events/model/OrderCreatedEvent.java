package bg.softuni.events.model;

import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class OrderCreatedEvent extends ApplicationEvent {

    private List<Long> allProductIds = new ArrayList<>();

    public OrderCreatedEvent(Object source) {
        super(source);
    }

    public List<Long> getAllProductIds() {
        return allProductIds;
    }

    public OrderCreatedEvent setAllProductIds(List<Long> allProductIds) {
        this.allProductIds = allProductIds;
        return this;
    }

    public OrderCreatedEvent addProduct(Long id) {
        this.allProductIds.add(id);
        return this;
    }
}
