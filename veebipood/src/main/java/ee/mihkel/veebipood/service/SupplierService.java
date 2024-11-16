package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.model.SupplierProduct;
import ee.mihkel.veebipood.model.SupplierProductEscuela;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SupplierService {

    public List<SupplierProduct> getProducts() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://fakestoreapi.com/products";
                                    // null --> Body ja Headers
        ResponseEntity<SupplierProduct[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                SupplierProduct[].class
        );
        // ResponseEntity sees on staatuskood + headerid + body

        if (response.getBody() == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(response.getBody());
    }



    public List<SupplierProductEscuela> getEscuelaProducts() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.escuelajs.co/api/v1/products";
        ResponseEntity<SupplierProductEscuela[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                SupplierProductEscuela[].class
        );
        if (response.getBody() == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(response.getBody());
    }


}
