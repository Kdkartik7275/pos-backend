package in.smartpos.smartpos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.smartpos.smartpos.dto.brand.BrandResponseDto;
import in.smartpos.smartpos.dto.brand.CreateBrandRequestDto;
import in.smartpos.smartpos.service.BrandService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/create")
    public ResponseEntity<BrandResponseDto> createBrand(@Valid @RequestBody CreateBrandRequestDto entity) {

        return brandService.createBrand(entity).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<BrandResponseDto>> getBrands(@RequestParam String shopId) {
        return brandService.getBrandsByShopId(shopId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get")
    public ResponseEntity<BrandResponseDto> getBrandById(@RequestParam Long id, @RequestParam String shopId) {
        return brandService.getBrandById(id, shopId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
