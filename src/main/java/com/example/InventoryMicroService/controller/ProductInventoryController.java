package com.example.InventoryMicroService.controller;


import com.example.InventoryMicroService.dto.Product;
import com.example.InventoryMicroService.dto.ProductInventoryDTO;

import com.example.InventoryMicroService.entity.Merchant;
import com.example.InventoryMicroService.entity.ProductInventory;
import com.example.InventoryMicroService.service.ProductInventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/productInventory")
public class ProductInventoryController {
    @Autowired
    ProductInventoryService productInventoryService;

    @PostMapping("/add")
    public ResponseEntity<ProductInventoryDTO> addProductInventory(@RequestBody ProductInventoryDTO productInventoryDTO) {
        try {
            ProductInventory productInventory = productInventoryService.addProductInventory(productInventoryDTO);
            ProductInventoryDTO newProductInventoryDTO = new ProductInventoryDTO();

            BeanUtils.copyProperties(productInventory, newProductInventoryDTO);

            return new ResponseEntity<>(newProductInventoryDTO,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("get-all-inventory")
    public ResponseEntity<List<ProductInventoryDTO>> getAllProdcutInventory() {
        List<ProductInventory> allProductInventory = productInventoryService.getAllProductInventory();
        List<ProductInventoryDTO> productInventoryDTOList = new ArrayList<>();
        for (ProductInventory productInventory : allProductInventory) {
            ProductInventoryDTO productInventoryDTO = new ProductInventoryDTO();
            BeanUtils.copyProperties(productInventory,productInventoryDTO);
            productInventoryDTOList.add(productInventoryDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(productInventoryDTOList);
    }

    @GetMapping("/get-product-inventory-by-id/{productInventoryId}")
    public ResponseEntity<ProductInventoryDTO> getProductInventoryById(@PathVariable String productInventoryId) {
        ProductInventory productInventory = productInventoryService.getProductInventoryById(productInventoryId);
        ProductInventoryDTO productInventoryDTO = new ProductInventoryDTO();

        BeanUtils.copyProperties(productInventory,productInventoryDTO);

        return ResponseEntity.status(HttpStatus.OK).body(productInventoryDTO);
    }

    @GetMapping("/get-products-by-merchant-id/{merchantId}")
    public ResponseEntity<List<ProductInventory>> getProductsByMerchant(@PathVariable String merchantId) {
        List<ProductInventory> productInventoryList = productInventoryService.findByMerchantId(merchantId);
        return new ResponseEntity<>(productInventoryList, HttpStatus.OK);
    }

    @GetMapping("/get-merchants-by-product-id/{productId}")
    public List<Merchant> findMerchantsByProductId(@PathVariable String productId) {
        return productInventoryService.findMerchantsByProductId(productId);
    }

    @GetMapping("/feign")
    public Product getProductById(@RequestParam String productId){
        return productInventoryService.getProductByFeign(productId);
    }


    @DeleteMapping("/delete-product-inventory-by-id/{inventoryId}")
    public ResponseEntity<Void> deleteProductInventoryById(@PathVariable String inventoryId) {
        try {
            productInventoryService.deleteProductInventory(inventoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update-product-inventory-by-id/{inventoryId}")
    public ResponseEntity<ProductInventoryDTO> updateProductInventory(@RequestBody ProductInventoryDTO productInventoryDTO, @PathVariable String inventoryId) {
        try {
            ProductInventory updateProductInventory = productInventoryService.editProductInventory(productInventoryDTO,inventoryId);
            ProductInventoryDTO updateProductInventoryDTO = new ProductInventoryDTO();
            BeanUtils.copyProperties(updateProductInventory, updateProductInventoryDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updateProductInventoryDTO);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
