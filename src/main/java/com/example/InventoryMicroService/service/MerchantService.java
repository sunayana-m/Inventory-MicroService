package com.example.InventoryMicroService.service;

import com.example.InventoryMicroService.dto.MerchantDTO;
import com.example.InventoryMicroService.entity.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant addMerchant(MerchantDTO merchantDTO);

    List<Merchant> getAllMerchants();

    Merchant getMerchantById(String merchantId);

    Merchant editMerchant(MerchantDTO merchantDTO, String merchantId);

    void deleteMerchant(String merchantId);
}
