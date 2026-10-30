package in.smartpos.smartpos.repository;

import org.springframework.stereotype.Component;

import in.smartpos.smartpos.model.Product;

@Component 
public class InventoryRepository {

    public Product getProducts(){
        return new Product();
    }

}
