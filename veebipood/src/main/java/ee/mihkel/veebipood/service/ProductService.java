package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public void validateProduct(Product product) throws ValidationException {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new ValidationException("Product name cannot be empty");
        }
    }
}
