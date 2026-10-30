package in.smartpos.smartpos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.smartpos.smartpos.dto.category.CategoryResponseDto;
import in.smartpos.smartpos.dto.category.CreateCategoryRequestDto;
import in.smartpos.smartpos.dto.category.UpdateCategoryRequestDto;
import in.smartpos.smartpos.service.CategoryService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController

@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CreateCategoryRequestDto requestDto) {

        CategoryResponseDto categoryResponse = categoryService.createCategory(requestDto);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(@RequestParam String shopId) {

        return categoryService.getCategoriesByShopId(shopId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@RequestParam Long id, @RequestParam String shopId) {

        return categoryService.getCategoryById(id, shopId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id,
            @RequestBody UpdateCategoryRequestDto requestDto) {

        return categoryService.updateCategory(id, requestDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id, @RequestParam String shopId) {

        return categoryService.deleteCategorySoftly(id, shopId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
