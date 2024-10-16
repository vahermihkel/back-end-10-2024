package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// CrudRepository
// PagingAndSortingRepository

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_Id(Long id);

//    @Query("SELECT * FROM products WHERE category_id = :id")
//    List<Product> findAllProductsByCategoryId(@Param("id")  Long id);
}
