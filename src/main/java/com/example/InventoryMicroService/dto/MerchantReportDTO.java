package com.example.InventoryMicroService.dto;

import lombok.Data;

@Data
public class MerchantReportDTO {
    private String id;
    private String productId;
    private int totalStock;
    private int totalSale;
    private int currentStock;
    private int merchantRating;
    private String merchant_fk;
}
