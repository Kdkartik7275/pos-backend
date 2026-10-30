package in.smartpos.smartpos.service;

import org.springframework.stereotype.Service;

import in.smartpos.smartpos.model.Product;
import in.smartpos.smartpos.repository.InventoryRepository;

@Service 
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    
    public Product getProducts(){
        return inventoryRepository.getProducts();
    }

}
