package in.smartpos.smartpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.smartpos.smartpos.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByIdAndShopIdAndDeletedFalse(Long id, String shopId, Boolean deleted);

    Optional<List<Brand>> findByShopIdAndDeletedFalse(String shopId, Boolean deleted);
}
