package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

//    private final OrderRepository orderRepository;
//
//    public OrderController(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    @GetMapping("order/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @PostMapping("order")
    public List<Order> saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return orderRepository.findByPerson_Email(order.getPerson().getEmail());
    }
}
