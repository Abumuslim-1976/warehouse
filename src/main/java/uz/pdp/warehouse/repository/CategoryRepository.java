package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouse.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByNameAndParentCategoryId(String name, Integer parentCategory_id);

    Optional<Category> findByParentCategoryId(Integer parentCategory_id);
}
