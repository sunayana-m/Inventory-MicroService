package com.example.InventoryMicroService.repository;

import com.example.InventoryMicroService.entity.MerchantReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface MerchantReportRepository extends CrudRepository<MerchantReport,String> {

  @Query("SELECT mr FROM MerchantReport mr WHERE mr.merchant.id = :merchantId")
  List<MerchantReport> getMerchantReportByMerchantId(@Param("merchantId") String merchantId);

}
