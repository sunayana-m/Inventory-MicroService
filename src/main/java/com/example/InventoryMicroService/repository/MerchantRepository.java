package com.example.InventoryMicroService.repository;

import com.example.InventoryMicroService.entity.Merchant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MerchantRepository extends CrudRepository<Merchant,String> {


}
