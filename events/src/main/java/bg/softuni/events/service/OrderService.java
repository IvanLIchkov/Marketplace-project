package bg.softuni.events.service;

import bg.softuni.events.model.OrderCreatedEvent;
import bg.softuni.events.model.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private ApplicationEventPublisher applicationEventPublisher;

    public OrderService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void createOrder(OrderDto orderDto){

        LOGGER.info("Order was created");
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(this)
                .setAllProductIds(orderDto.getAllProductIds());
        applicationEventPublisher.publishEvent(orderCreatedEvent);
    }
}
