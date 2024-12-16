package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.cache.ProductCache;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.OrderRow;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.model.payment.*;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Value("${everypay-url}")
    String everyPayUrl;

    @Value("${everypay-username}")
    String everyPayUsername;

    @Value("${everypay-authorization}")
    String everyPayAuthorization;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductCache productCache;

    public Order saveOrder(Order order) throws ExecutionException {
        //List<OrderRow> orderRows = orderRowRepository.saveAll(order.getOrderRows());
        // order.setOrderRows(orderRows);

        order.setCreation(new Date());
        double totalSum = 0;
        for(OrderRow row: order.getOrderRows()){
//            Product dbProduct = productRepository.findById(row.getProduct().getId()).orElseThrow();
            Product dbProduct = productCache.getProduct(row.getProduct().getId());
            totalSum += dbProduct.getPrice() * row.getPcs();
        }
        order.setTotalSum(totalSum);
        return orderRepository.save(order);
    }

    public PaymentLink getPaymentLink(Order order) {
        //RestTemplate restTemplate = new RestTemplate();
        String url = everyPayUrl + "/api/v4/payments/oneoff";

        EveryPayBody body = new EveryPayBody();
        body.setAccount_name("EUR3D1");
        body.setNonce("asd123" + ZonedDateTime.now() + Math.random());
        body.setTimestamp(ZonedDateTime.now().toString());
        body.setAmount(order.getTotalSum());
        body.setOrder_reference(order.getId().toString());
        body.setCustomer_url("https://err.ee");
        body.setApi_username(everyPayUsername);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, everyPayAuthorization);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<EveryPayBody> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                EveryPayResponse.class
        );

        PaymentLink link = new PaymentLink();
        link.setLink(response.getBody().getPayment_link());
        return link;
    }

    public PaymentStatus checkPaymentStatus(String paymentReference) {
        //RestTemplate restTemplate = new RestTemplate();

        String url = everyPayUrl + "/api/v4/payments/" + paymentReference +
                "?api_username=" +
                everyPayUsername +
                "&detailed=false";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, everyPayAuthorization);
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<PaymentStatusResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                PaymentStatusResponse.class
        );

        PaymentStatus status = new PaymentStatus();
        status.setStatus(response.getBody().getPayment_state());
        return status;
    }
}
