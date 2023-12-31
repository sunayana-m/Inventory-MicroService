package com.example.InventoryMicroService.service;

import com.example.InventoryMicroService.dto.Product;
import com.example.InventoryMicroService.dto.ProductInventoryDTO;
import com.example.InventoryMicroService.entity.Merchant;
import com.example.InventoryMicroService.entity.ProductInventory;

import java.util.List;

public interface ProductInventoryService {
    List<Merchant> findMerchantsByProductId(String productId);

    ProductInventory addProductInventory(ProductInventoryDTO productInventoryDTO);

    List<ProductInventory> getAllProductInventory();

    ProductInventory getProductInventoryById(String inventoryId);

    ProductInventory editProductInventory(ProductInventoryDTO productInventoryDTO, String inventoryId);

    void deleteProductInventory(String inventoryId);

    List<ProductInventory> findByMerchantId(String merchantId);

    Product getProductByFeign(String productId);
}
