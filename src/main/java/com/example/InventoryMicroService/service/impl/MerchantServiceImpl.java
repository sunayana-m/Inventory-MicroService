package com.example.InventoryMicroService.service.impl;

import com.example.InventoryMicroService.dto.MerchantDTO;
import com.example.InventoryMicroService.entity.Merchant;
import com.example.InventoryMicroService.repository.MerchantRepository;
import com.example.InventoryMicroService.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant addMerchant(MerchantDTO merchantDTO){


        Merchant merchant = new Merchant();

        BeanUtils.copyProperties(merchantDTO, merchant);
        merchant.setId(UUID.randomUUID().toString());
        merchantRepository.save(merchant);
        return merchant;
    }


    @Override
    public List<Merchant> getAllMerchants() {
        Iterable<Merchant> allMerchants = merchantRepository.findAll();
        List<Merchant> merchantsList = new ArrayList<>();
        allMerchants.forEach(merchantsList::add);
        return merchantsList;
    }

    @Override
    public Merchant getMerchantById(String merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId).get();
        return merchant;
    }

    @Override
    public Merchant editMerchant(MerchantDTO merchantDTO, String merchantId) {
        Optional<Merchant> originalMerchant = merchantRepository.findById(merchantId);
        if (originalMerchant.isPresent()) {
            Merchant merchant = originalMerchant.get();
            BeanUtils.copyProperties(merchantDTO, merchant);
            merchant.setId(merchantId);

            merchantRepository.save(merchant);
            return merchant;
        } else {
            throw new EntityNotFoundException("Merchant not found with ID: " + merchantId);
        }
    }

    @Override
    public void deleteMerchant(String merchantId) {
        try {
            merchantRepository.deleteById(merchantId);
        } catch (Exception e) {
            throw new EntityNotFoundException("Merchant not found with ID: " + merchantId);
        }
    }
}
