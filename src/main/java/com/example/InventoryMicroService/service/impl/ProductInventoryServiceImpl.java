package com.example.InventoryMicroService.service.impl;

import com.example.InventoryMicroService.dto.MerchantDTO;
import com.example.InventoryMicroService.dto.ProductInventoryDTO;
import com.example.InventoryMicroService.entity.Merchant;
import com.example.InventoryMicroService.entity.ProductInventory;
import com.example.InventoryMicroService.repository.MerchantRepository;
import com.example.InventoryMicroService.repository.ProductInventoryRepository;
import com.example.InventoryMicroService.service.MerchantService;
import com.example.InventoryMicroService.service.ProductInventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    ProductInventoryRepository productInventoryRepository;

    @Autowired
    MerchantService merchantService;


    @Override
    public ProductInventory addProductInventory(ProductInventoryDTO productInventoryDTO){
        ProductInventory productInventory = new ProductInventory();
        BeanUtils.copyProperties(productInventoryDTO, productInventory);
        String merchant_id = productInventoryDTO.getMerchant_fk1();
        Merchant merchant = merchantService.getMerchantById(merchant_id);
        productInventory.setMerchant1(merchant);
        productInventoryRepository.save(productInventory);
        return productInventory;
    }


    @Override
    public List<ProductInventory> getAllProductInventory() {
        Iterable<ProductInventory> allProducts = productInventoryRepository.findAll();
        List<ProductInventory> productsList = new ArrayList<>();
        allProducts.forEach(productsList::add);
        return productsList;
    }

    @Override
    public ProductInventory getProductInventoryById(String inventoryId) {
        ProductInventory productInventory = productInventoryRepository.findById(inventoryId).get();
        return productInventory;
    }

    @Override
    public ProductInventory editProductInventory(ProductInventoryDTO productInventoryDTO, String inventoryId) {
        Optional<ProductInventory> original = productInventoryRepository.findById(inventoryId);
        if (original.isPresent()) {
            ProductInventory productInventory = original.get();
            BeanUtils.copyProperties(productInventoryDTO, productInventory);
            productInventory.setId(inventoryId);

            productInventoryRepository.save(productInventory);
            return productInventory;
        } else {
            throw new EntityNotFoundException("Student not found with ID: " + inventoryId);
        }
    }

    @Override
    public void deleteProductInventory(String inventoryId) {
        try {
            productInventoryRepository.deleteById(inventoryId);
        } catch (Exception e) {
            throw new EntityNotFoundException("Course not found with ID: " + inventoryId);
        }
    }

}
