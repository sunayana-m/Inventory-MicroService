package com.example.InventoryMicroService.repository;

import com.example.InventoryMicroService.entity.ProductInventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory, String> {
}
