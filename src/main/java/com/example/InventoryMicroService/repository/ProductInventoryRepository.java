package com.example.InventoryMicroService.repository;

import com.example.InventoryMicroService.entity.ProductInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory, String> {
    List<ProductInventory> findByMerchant1Id(String merchantId);

    @Query("SELECT DISTINCT pi.merchant1.id FROM ProductInventory pi WHERE pi.productId = :productId")
    List<String> findMerchantIdsByProductId(@Param("productId") String productId);


}
