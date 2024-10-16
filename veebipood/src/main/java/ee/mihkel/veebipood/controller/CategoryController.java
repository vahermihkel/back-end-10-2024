package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Category;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.CategoryRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import ee.mihkel.veebipood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    // Dependency Injection

//    private final CategoryRepository categoryRepository;
//
//    public CategoryController(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }

    @GetMapping("category")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("category")
    public List<Category> addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return categoryRepository.findAll();
    }

    @GetMapping("category-products/{categoryId}")
    public List<Product> getCategoryProducts(@PathVariable Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }

    @GetMapping("protein-category-products/{categoryId}")
    public int getProteinCategoryProducts(@PathVariable Long categoryId) {
       List<Product> products = productRepository.findByCategory_Id(categoryId);
        return categoryService.getProteins(products);
    }



//    @GetMapping("category-products2/{id}")
//    public List<Product> getCategoryProducts2(@PathVariable Long categoryId) {
//        return productRepository.findAllProductsByCategoryId(categoryId);
//    }

    // localhost:8080/category?categoryId=1&productId=4
//    @PatchMapping("category")
//    public List<Category> addProductToCategory(@RequestParam Long categoryId,
//                                               @RequestParam Long productId) {
//        Product product = productRepository.findById(productId).get();
//        Category category = categoryRepository.findById(categoryId).get();
//        List<Product> productsInCategory = category.getProducts();
//        productsInCategory.add(product);
////        category.setProducts(productsInCategory);
//        categoryRepository.save(category);
//        return categoryRepository.findAll();
//    }
}