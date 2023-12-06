package com.example.InventoryMicroService.service.impl;

import com.example.InventoryMicroService.dto.MerchantReportDTO;

import com.example.InventoryMicroService.entity.Merchant;
import com.example.InventoryMicroService.entity.MerchantReport;
import com.example.InventoryMicroService.repository.MerchantReportRepository;
import com.example.InventoryMicroService.repository.MerchantRepository;
import com.example.InventoryMicroService.service.MerchantReportService;
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
public class MerchantReportServiceImpl implements MerchantReportService {

    @Autowired
    MerchantReportRepository merchantReportRepository;

    @Autowired
    MerchantService merchantService;

    @Override
    public MerchantReport addMerchantReport(MerchantReportDTO merchantReportDTO){

        MerchantReport merchantReport = new MerchantReport();

        BeanUtils.copyProperties(merchantReportDTO, merchantReport);
        String merchant_id = merchantReportDTO.getMerchant_fk();
        Merchant merchant = merchantService.getMerchantById(merchant_id);
        merchantReport.setMerchant(merchant);

        merchantReportRepository.save(merchantReport);

        return merchantReport;
    }


    @Override
    //public List<ProductResponseDto> getAllProduct() {
    //        log.debug("Inside get all product");
    //
    //        Iterable<Product> productList = productRepository.findAll();
    //        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
    //        for (Product product : productList) {
    //            ProductResponseDto productResponseDto = new ProductResponseDto();
    //            BeanUtils.copyProperties(product, productResponseDto);
    //            productResponseDtoList.add(productResponseDto);
    //        }
    //        return productResponseDtoList;
    //    }
    public List<MerchantReport> getAllMerchantReports() {
        Iterable<MerchantReport> allMerchantReports = merchantReportRepository.findAll();
        List<MerchantReport> merchantReportsList = new ArrayList<>();
        allMerchantReports.forEach(merchantReportsList::add);
        return merchantReportsList;
    }

    @Override
    public MerchantReport getMerchantReportById(String merchantReportId) {
        MerchantReport merchantReport = merchantReportRepository.findById(merchantReportId).get();
        return merchantReport;
    }


    @Override
    public MerchantReport editMerchantReport(MerchantReportDTO merchantReportDTO, String merchantReportId) {
        Optional<MerchantReport> originalMerchantReport = merchantReportRepository.findById(merchantReportId);
        if (originalMerchantReport.isPresent()) {
            MerchantReport merchantReport = originalMerchantReport.get();
            BeanUtils.copyProperties(merchantReportDTO, merchantReport);
            merchantReport.setId(merchantReportId);

            merchantReportRepository.save(merchantReport);
            return merchantReport;
        } else {
            throw new EntityNotFoundException("Merchant not found with ID: " + merchantReportId);
        }
    }

    @Override
    public void deleteMerchantReport(String merchantReportId) {
        try {
            merchantReportRepository.deleteById(merchantReportId);
        } catch (Exception e) {
            throw new EntityNotFoundException("Merchant not found with ID: " + merchantReportId);
        }
    }

    @Override
    public List<MerchantReport> getMerchantReportByMerchantId(String merchantId) {
        return merchantReportRepository.getMerchantReportByMerchantId(merchantId);
    }
}
