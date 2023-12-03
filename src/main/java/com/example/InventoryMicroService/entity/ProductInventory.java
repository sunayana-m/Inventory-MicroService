package com.example.InventoryMicroService.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = ProductInventory.TABLE_NAME)
@Getter
@Setter
public class ProductInventory {

    public static final String TABLE_NAME = "product_inventory";

    @Id
    @Column(name=Merchant.ID_COLUMN_NAME)
    @GeneratedValue(generator = Merchant.SEQ_GEN_ALIAS)
    @GenericGenerator(name=Merchant.SEQ_GEN_ALIAS,strategy = Merchant.SEQ_GEN_STRATEGY)
    private String id;

    private String productId;

    private int stock;
    private double price;
    private float discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_fk1",nullable = false)
    private Merchant merchant1;

}
