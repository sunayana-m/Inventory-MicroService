package com.example.InventoryMicroService.controller;

import com.example.InventoryMicroService.dto.MerchantDTO;
import com.example.InventoryMicroService.entity.Merchant;
import com.example.InventoryMicroService.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping
    public ResponseEntity<MerchantDTO> addMerchant(@RequestBody MerchantDTO merchantDTO) {
        try {
            Merchant merchant = merchantService.addMerchant(merchantDTO);
            MerchantDTO newMerchant = new MerchantDTO();

            BeanUtils.copyProperties(merchant, newMerchant);


            return ResponseEntity.status(HttpStatus.CREATED).body(newMerchant);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        List<Merchant> allMerchants = merchantService.getAllMerchants();
        List<MerchantDTO> merchantDTOList = new ArrayList<>();
        for (Merchant merchant : allMerchants) {
            MerchantDTO merchantDTO = new MerchantDTO();
            BeanUtils.copyProperties(merchant,merchantDTO);
            merchantDTOList.add(merchantDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(merchantDTOList);
    }


    @DeleteMapping("/delete/{merchantId}")
    public ResponseEntity<Void> deleteMerchantById(@PathVariable String merchantId) {
        try {
            merchantService.deleteMerchant(merchantId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update/{merchantId}")
    public ResponseEntity<MerchantDTO> updateMerchant(@RequestBody MerchantDTO merchantDTO, @PathVariable String merchantId) {
        try {
            Merchant updateMerchant = merchantService.editMerchant(merchantDTO,merchantId);
            MerchantDTO updateMerchantDTO = new MerchantDTO();
            BeanUtils.copyProperties(updateMerchant, updateMerchantDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updateMerchantDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}