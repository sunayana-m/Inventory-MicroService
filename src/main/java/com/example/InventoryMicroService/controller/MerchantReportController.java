package com.example.InventoryMicroService.controller;

import com.example.InventoryMicroService.dto.MerchantReportDTO;
import com.example.InventoryMicroService.entity.MerchantReport;
import com.example.InventoryMicroService.service.MerchantReportService;
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
@RequestMapping("/merchantReport")
public class MerchantReportController {
    @Autowired
    MerchantReportService merchantReportService;
    @Autowired
    MerchantService merchantService;

    @PostMapping
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

    @GetMapping
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

    @GetMapping("/get/{merchantReportId}")
    public ResponseEntity<MerchantReportDTO> getMerchantReportsById(@PathVariable String merchantReportId) {
        MerchantReport merchantReport = merchantReportService.getMerchantReportById(merchantReportId);
        MerchantReportDTO merchantReportDTO = new MerchantReportDTO();

            BeanUtils.copyProperties(merchantReport,merchantReportDTO);

        return ResponseEntity.status(HttpStatus.OK).body(merchantReportDTO);
    }



    @DeleteMapping("/delete/{merchantReportId}")
    public ResponseEntity<Void> deleteMerchantReportById(@PathVariable String merchantReportId) {
        try {
            merchantReportService.deleteMerchantReport(merchantReportId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update/{merchantReportId}")
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

    @GetMapping("/getMerchantReportById")
    public ResponseEntity<List<MerchantReport>> getMerchantReport(@RequestParam String merchantId){

        return new ResponseEntity<>(merchantReportService.getMerchantReportByMerchantId(merchantId),HttpStatus.OK);
    }

}
