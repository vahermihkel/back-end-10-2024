package ee.mihkel.veebipood.repository;


import ee.mihkel.veebipood.entity.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
}
