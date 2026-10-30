package in.smartpos.smartpos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.smartpos.smartpos.model.Product;
import in.smartpos.smartpos.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping("/")
    public Product getProducts() {
        return inventoryService.getProducts();
    }

}
