package com.example.InventoryMicroService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = MerchantReport.TABLE_NAME)
@Getter
@Setter
public class MerchantReport {

    public static final String TABLE_NAME = "merchantReport";

    @Id
    @Column(name=Merchant.ID_COLUMN_NAME)
    @GeneratedValue(generator = Merchant.SEQ_GEN_ALIAS)
    @GenericGenerator(name=Merchant.SEQ_GEN_ALIAS,strategy = Merchant.SEQ_GEN_STRATEGY)
    private String id;

    private String productId;
    private int totalStock;
    private int totalSale;
    private int currentStock;
    private int merchantRating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_fk",nullable = false)
    private Merchant merchant;

}
