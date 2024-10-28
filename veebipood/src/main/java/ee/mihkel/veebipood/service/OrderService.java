package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.OrderRow;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public void saveOrder(Order order) {
        //List<OrderRow> orderRows = orderRowRepository.saveAll(order.getOrderRows());
        // order.setOrderRows(orderRows);

        order.setCreation(new Date());
        double totalSum = 0;
        for(OrderRow row: order.getOrderRows()){
            Product dbProduct = productRepository.findById(row.getProduct().getId()).orElseThrow();
            totalSum += dbProduct.getPrice() * row.getPcs();
        }
        order.setTotalSum(totalSum);
        orderRepository.save(order);
    }
}
