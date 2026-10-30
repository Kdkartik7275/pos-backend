package in.smartpos.smartpos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.smartpos.smartpos.dto.category.CategoryResponseDto;
import in.smartpos.smartpos.dto.category.CreateCategoryRequestDto;
import in.smartpos.smartpos.dto.category.UpdateCategoryRequestDto;
import in.smartpos.smartpos.model.Category;
import in.smartpos.smartpos.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto) {

        Category category = toEntity(requestDto);

        Category savedCategory = categoryRepository.save(category);

        return toResponseDto(savedCategory);
    }

    public Optional<List<CategoryResponseDto>> getCategoriesByShopId(String shopId) {
        return categoryRepository.findByShopIdAndDeletedFalse(shopId, false)
                .map(categories -> categories.stream().map(this::toResponseDto).toList());
    }

    public Optional<CategoryResponseDto> getCategoryById(Long id, String shopId) {
        return categoryRepository.findByIdAndShopIdAndDeletedFalse(id, shopId, false).map(this::toResponseDto);
    }

    public Optional<CategoryResponseDto> updateCategory(Long id, UpdateCategoryRequestDto requestDto) {
        return categoryRepository.findByIdAndShopIdAndDeletedFalse(id, requestDto.getShopId(), false)
                .map(existingCategory -> {
                    existingCategory.setName(requestDto.getName());
                    existingCategory.setUpdatedAt(LocalDateTime.now());
                    Category updatedCategory = categoryRepository.save(existingCategory);
                    return toResponseDto(updatedCategory);
                });
    }

    public Optional<String> deleteCategorySoftly(Long id, String shopId) {

        return categoryRepository.findByIdAndShopIdAndDeletedFalse(id, shopId, false).map(existingCategory -> {

            existingCategory.setDeleted(true);
            existingCategory.setUpdatedAt(LocalDateTime.now());

            categoryRepository.save(existingCategory);

            return "Category deleted successfully";
        });
    }

    private Category toEntity(CreateCategoryRequestDto requestDto) {

        Category category = new Category();

        category.setName(requestDto.getName());
        category.setShopId(requestDto.getShopId());
        category.setDeleted(false);
        category.setCreatedAt(LocalDateTime.now());

        return category;
    }

    private CategoryResponseDto toResponseDto(Category category) {

        return new CategoryResponseDto(category.getId(), category.getName(), category.getShopId(),
                category.getCreatedAt(), category.getUpdatedAt());
    }
}