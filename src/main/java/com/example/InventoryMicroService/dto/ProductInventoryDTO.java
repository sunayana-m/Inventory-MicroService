package com.example.InventoryMicroService.dto;

import lombok.Data;

@Data
public class ProductInventoryDTO {

    private String id;

    private String productId;

    private int stock;
    private double price;
    private float discount;
    private String merchant_fk1;
}
