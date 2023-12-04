package com.example.InventoryMicroService.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Category {
    private String categoryName;
    private int categoryOffer;
    private List<Product> productList = new ArrayList<>();
}
