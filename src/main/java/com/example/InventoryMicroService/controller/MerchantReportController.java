package com.example.InventoryMicroService.controller;

import com.example.InventoryMicroService.dto.MerchantReportDTO;
import com.example.InventoryMicroService.entity.MerchantReport;
import com.example.InventoryMicroService.service.MerchantReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merchantReport")
public class MerchantReportController {

    @Autowired
    MerchantReportService merchantReportService;


    @PostMapping("/add")
    public ResponseEntity<MerchantReportDTO> addMerchantReport(@RequestBody MerchantReportDTO merchantReportDTO) {
        try {
            MerchantReport merchantReport = merchantReportService.addMerchantReport(merchantReportDTO);
            MerchantReportDTO newMerchantReport = new MerchantReportDTO();

            BeanUtils.copyProperties(merchantReport, newMerchantReport);


            return ResponseEntity.status(HttpStatus.CREATED).body(newMerchantReport);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/get-all-reports")
    public ResponseEntity<List<MerchantReportDTO>> getAllMerchantReports() {
        List<MerchantReport> allMerchantReports = merchantReportService.getAllMerchantReports();
        List<MerchantReportDTO> merchantReportDTOList = new ArrayList<>();
        for (MerchantReport merchantReport : allMerchantReports) {
            MerchantReportDTO merchantReportDTO = new MerchantReportDTO();
            BeanUtils.copyProperties(merchantReport,merchantReportDTO);

            merchantReportDTOList.add(merchantReportDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(merchantReportDTOList);
    }

    @GetMapping("/get-merchant-report-by-id/{merchantReportId}")
    public ResponseEntity<MerchantReportDTO> getMerchantReportsById(@PathVariable String merchantReportId) {
        MerchantReport merchantReport = merchantReportService.getMerchantReportById(merchantReportId);
        MerchantReportDTO merchantReportDTO = new MerchantReportDTO();

            BeanUtils.copyProperties(merchantReport,merchantReportDTO);

        return ResponseEntity.status(HttpStatus.OK).body(merchantReportDTO);
    }

    @GetMapping("/get-by-merchant-id/{merchantId}")
    public ResponseEntity<List<MerchantReport>> getMerchantReportsByMerchantId(@PathVariable String merchantId) {
        List<MerchantReport> merchantReports = merchantReportService.getMerchantReportByMerchantId(merchantId);
            return new ResponseEntity<>(merchantReports, HttpStatus.OK);
    }


    @DeleteMapping("/delete-by-merchant-report-id/{merchantReportId}")
    public ResponseEntity<Void> deleteMerchantReportById(@PathVariable String merchantReportId) {
        try {
            merchantReportService.deleteMerchantReport(merchantReportId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update-b-merchant-report-id/{merchantReportId}")
    public ResponseEntity<MerchantReportDTO> updateMerchantReport(@RequestBody MerchantReportDTO merchantReportDTO, @PathVariable String merchantReportId) {
        try {
            MerchantReport updateMerchantReport = merchantReportService.editMerchantReport(merchantReportDTO,merchantReportId);
            MerchantReportDTO updateMerchantReportDTO = new MerchantReportDTO();
            BeanUtils.copyProperties(updateMerchantReport, updateMerchantReportDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updateMerchantReportDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
