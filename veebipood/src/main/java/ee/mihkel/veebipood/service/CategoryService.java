package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    public int getProteins(List<Product> products) {
        int sum = 0;
        for (Product p: products) {
            if (p.getNutrients() != null) {
                sum += p.getNutrients().getProtein();
            }
        }
        return sum;
    }
}
