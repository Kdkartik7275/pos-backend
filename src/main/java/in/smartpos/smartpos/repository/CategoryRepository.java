package in.smartpos.smartpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.smartpos.smartpos.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> findByShopIdAndDeletedFalse(String shopId, Boolean deleted);

    Optional<Category> findByIdAndShopIdAndDeletedFalse(Long id, String shopId, Boolean deleted);

}
