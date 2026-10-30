package in.smartpos.smartpos.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import in.smartpos.smartpos.dto.brand.BrandResponseDto;
import in.smartpos.smartpos.dto.brand.CreateBrandRequestDto;
import in.smartpos.smartpos.model.Brand;
import in.smartpos.smartpos.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Optional<BrandResponseDto> createBrand(CreateBrandRequestDto requestDto) {

        Brand brand = toEntity(requestDto);

        Brand savedBrand = brandRepository.save(brand);

        return Optional.of(toResponseDto(savedBrand));
    }

    public Optional<List<BrandResponseDto>> getBrandsByShopId(String shopId) {
        return brandRepository.findByShopIdAndDeletedFalse(shopId, false)
                .map(brands -> brands.stream().map(this::toResponseDto).toList());
    }

    public Optional<BrandResponseDto> getBrandById(Long id, String shopId) {
        return brandRepository.findByIdAndShopIdAndDeletedFalse(id, shopId, false).map(this::toResponseDto);
    }

    private Brand toEntity(CreateBrandRequestDto requestDto) {

        Brand brand = new Brand();

        brand.setName(requestDto.getName());
        brand.setSearchName(requestDto.getName().toLowerCase().replaceAll("\\s+", ""));
        brand.setShopId(requestDto.getShopId());
        brand.setDeleted(false);
        brand.setCreatedAt(LocalDateTime.now());

        return brand;
    }

    private BrandResponseDto toResponseDto(Brand brand) {

        return new BrandResponseDto(brand.getId(), brand.getName(), brand.getSearchName(), brand.getShopId(),
                brand.getCreatedAt(), brand.getUpdatedAt());
    }
}