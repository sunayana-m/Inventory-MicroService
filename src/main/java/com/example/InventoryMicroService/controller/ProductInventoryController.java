package com.example.InventoryMicroService.controller;


import com.example.InventoryMicroService.dto.Product;
import com.example.InventoryMicroService.dto.ProductInventoryDTO;

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

@RestController
@RequestMapping("/productInventory")
public class ProductInventoryController {
    @Autowired
    ProductInventoryService productInventoryService;

    @PostMapping
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

    @GetMapping
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

    @GetMapping("/get/{productInventoryId}")
    public ResponseEntity<ProductInventoryDTO> getMerchantReportsById(@PathVariable String productInventoryId) {
        ProductInventory productInventory = productInventoryService.getProductInventoryById(productInventoryId);
        ProductInventoryDTO productInventoryDTO = new ProductInventoryDTO();

        BeanUtils.copyProperties(productInventory,productInventoryDTO);

        return ResponseEntity.status(HttpStatus.OK).body(productInventoryDTO);
    }

    @GetMapping("/feign")
    public List<Product> getProducts(){
        return productInventoryService.getProdutsByFeign();
    }


    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<Void> deleteProductInventoryById(@PathVariable String inventoryId) {
        try {
            productInventoryService.deleteProductInventory(inventoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update/{inventoryId}")
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
