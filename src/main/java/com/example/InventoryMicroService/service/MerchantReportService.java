package com.example.InventoryMicroService.service;

import com.example.InventoryMicroService.dto.MerchantReportDTO;
import com.example.InventoryMicroService.entity.MerchantReport;

import java.util.List;

public interface MerchantReportService {
    MerchantReport addMerchantReport(MerchantReportDTO merchantReportDTO);

    List<MerchantReport> getAllMerchantReports();

    MerchantReport getMerchantReportById(String merchantReportId);

    MerchantReport editMerchantReport(MerchantReportDTO merchantReportDTO, String merchantReportId);

    void deleteMerchantReport(String merchantReportId);

    List<MerchantReport> getMerchantReportByMerchantId(String merchantId);
}
