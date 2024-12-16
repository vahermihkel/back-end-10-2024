package ee.mihkel.veebipood.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ProductCache {

    @Autowired
    ProductRepository productRepository;

    private final LoadingCache<Long, Product> productLoadingCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(20, TimeUnit.SECONDS)
            .maximumSize(50)
            .build(new CacheLoader<Long, Product>() {
                @Override
                public Product load(Long id) {
                    System.out.println("Võtsin andmebaasist");
                    return productRepository.findById(id).orElseThrow(); // see on tegevus kui teda pole cache-s
                }
            });

    public Product getProduct(Long id) throws ExecutionException {
        System.out.println("Võtan toodet");
        return productLoadingCache.get(id);
    }

    public void emptyCache() {
        productLoadingCache.invalidateAll();
    }
}
