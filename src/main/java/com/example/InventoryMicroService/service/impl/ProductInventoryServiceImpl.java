package com.example.InventoryMicroService.service.impl;

import com.example.InventoryMicroService.dto.Product;
import com.example.InventoryMicroService.dto.ProductInventoryDTO;
import com.example.InventoryMicroService.entity.Merchant;
import com.example.InventoryMicroService.entity.ProductInventory;
import com.example.InventoryMicroService.feignClient.ProductServiceFeign;
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

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    ProductInventoryRepository productInventoryRepository;

    @Autowired
    MerchantRepository merchantRepository;


    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductServiceFeign productFeign;

    @Override
    public List<Merchant> findMerchantsByProductId(String productId) {
        // Step 1: Fetch merchant IDs by product ID
        List<String> merchantIds = productInventoryRepository.findMerchantIdsByProductId(productId);

        // Step 2: Fetch merchant details by the list of merchant IDs
        List<Merchant> merchants = merchantRepository.findByIdIn(merchantIds);

        return merchants;
    }


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

    @Override
    public List<ProductInventory> findByMerchantId(String merchantId) {
        return productInventoryRepository.findByMerchant1Id(merchantId);
    }

    @Override
    public Product getProductByFeign(String productId) {
        return productFeign.getProductById(productId);
    }

}
