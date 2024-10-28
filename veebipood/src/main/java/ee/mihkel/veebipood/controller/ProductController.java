package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Nutrients;
import ee.mihkel.veebipood.repository.ProductRepository;
import ee.mihkel.veebipood.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // annotatsioon @ -> sellega võtab API päringuid vastu
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
//    List<Product> products = new ArrayList<>(Arrays.asList(
//            new Product("Coca"),
//            new Product("Fanta"),
//            new Product("Sprite")
//    ));
//    List<String> productsEmpty = new ArrayList<>();

    @Autowired
    ProductRepository productRepository;

    // localhost:8080/products
    @GetMapping("/all-products")
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // SELECT * FROM product;
    }

    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable); // SELECT * FROM product;
    }

    @GetMapping("/product")
    public Product getProduct(@RequestParam Long id) {
        return productRepository.findById(id).orElseThrow(); // .get() ja .orElseThrow() <--- samad
    }

    // localhost:8080/add-product?name=Vichy&price=1
    // localhost:8080/add-product/Vichy
    @GetMapping("/add-product")
    public List<Product> addProduct(@RequestParam String name, @RequestParam double price) {
//        products.add(new Product(name));
        productRepository.save(new Product(name));
        return productRepository.findAll();
    }

    // localhost:8080/add-product?name=Vichy&kategooria=Vesi   <--- järjekord pole tähtis
    // localhost:8080/add-product/Vichy/vesi    <--- järjekord on tähtis

    // localhost:8080/delete-product/0
    @GetMapping("/delete-product/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping("/product")
    public List<Product> saveProduct(@RequestBody Product product) {
//        Nutrients nutrients = nutrientsRepository.save(product.getNutrients());
//        product.setNutrients(nutrients);
        productRepository.save(product);
        return productRepository.findAll();
    }

//    public String[] getProductsAsArray() {
//        String[] stringarray = {"Coca", "Fanta", "Sprite"};
//        return stringarray;
//    }
}

// 18.10 R kell 9.00.
// 1. andmebaas ja sidumine
// 2. PostMapping, PutMapping -> Postman
// 3. Service, kaustade struktuur
// 4. @Autowired
// 22.10 T kell 9.00
