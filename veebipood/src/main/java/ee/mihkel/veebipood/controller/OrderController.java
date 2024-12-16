package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.model.payment.PaymentLink;
import ee.mihkel.veebipood.model.payment.PaymentStatus;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

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
    public PaymentLink saveOrder(@RequestBody Order order) throws ExecutionException {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = new Person();
        person.setEmail(email);
        order.setPerson(person); // kuna on ainult e-mail tähtis, siis ei pea võtma andmebaasist tervet Personit
        order.setPaid(false);
        Order savedOrder = orderService.saveOrder(order);
        return orderService.getPaymentLink(savedOrder);
    }

    @GetMapping("check-payment/{paymentReference}")
    public PaymentStatus getOrderById(@PathVariable String paymentReference) {
        return orderService.checkPaymentStatus(paymentReference);
    }
}
