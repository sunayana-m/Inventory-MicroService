package com.example.InventoryMicroService.repository;

import com.example.InventoryMicroService.entity.Merchant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MerchantRepository extends CrudRepository<Merchant,String> {
    List<Merchant> findByIdIn(List<String> ids);


}
