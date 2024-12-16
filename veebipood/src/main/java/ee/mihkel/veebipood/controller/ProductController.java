package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.cache.ProductCache;
import ee.mihkel.veebipood.exception.ValidationException;
import ee.mihkel.veebipood.repository.ProductRepository;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Log4j2
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

    @Autowired
    ProductService productService;

    @Autowired
    ProductCache productCache;

    // localhost:8080/products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // SELECT * FROM product;
    }

    @GetMapping("/public-products")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @GetMapping("/product")
    public Product getProduct(@RequestParam Long id) throws ExecutionException {
        return productCache.getProduct(id);
//        return productRepository.findById(id).orElse(null); // .get() ja .orElseThrow() <--- samad
    }

    // localhost:8080/add-product?name=Vichy&price=1
    // localhost:8080/add-product/Vichy
//    @PostMapping("/products")
//    public List<Product> addProduct(@RequestParam String name, @RequestParam double price) {
////        products.add(new Product(name));
//        productRepository.save(new Product(name));
//        return productRepository.findAll();
//    }

    // localhost:8080/add-product?name=Vichy&kategooria=Vesi   <--- järjekord pole tähtis
    // localhost:8080/add-product/Vichy/vesi    <--- järjekord on tähtis

    // localhost:8080/delete-product/0
    @DeleteMapping("/products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        productCache.emptyCache();
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public List<Product> saveProduct(@RequestBody Product product) throws ValidationException {
        productService.validateProduct(product);
        if (productRepository.findById(product.getId()).isEmpty()) {
            productRepository.save(product);
        }
        return productRepository.findAll();
    }

    @PutMapping("/products")
    public List<Product> editProduct(@RequestBody Product product) throws ValidationException {
        productService.validateProduct(product);
        if (productRepository.findById(product.getId()).isPresent()) {
            productRepository.save(product);
            productCache.emptyCache();
        }
        return productRepository.findAllByOrderByIdAsc();
    }

//    public String[] getProductsAsArray() {
//        String[] stringarray = {"Coca", "Fanta", "Sprite"};
//        return stringarray;
//    }

    @GetMapping("/find-by-name")
    public Page<Product> findProductsByName(@RequestParam String name, Pageable pageable) {
        return productRepository.findByNameContainsIgnoreCase(name, pageable);
    }
}
