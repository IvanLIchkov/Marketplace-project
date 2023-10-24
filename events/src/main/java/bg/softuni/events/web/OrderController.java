package bg.softuni.events.web;

import bg.softuni.events.model.OrderDto;
import bg.softuni.events.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/dummy/create")
    void createDummyOrder(){
        OrderDto dummyOrder = new OrderDto();
        Random random = new Random();
        for(int i=0; i<3; i++){
            dummyOrder.addProduct(random.nextLong(100));
        }
        orderService.createOrder(dummyOrder);
    }
}
