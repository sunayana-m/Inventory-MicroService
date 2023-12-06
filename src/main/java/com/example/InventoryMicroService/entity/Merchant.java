package com.example.InventoryMicroService.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Merchant.TABLE_NAME)
@Getter
@Setter
public class Merchant {
    public static final String TABLE_NAME = "merchant";
    public static final String ID_COLUMN_NAME="ID";
    public static final String FIRST_NAME_COLUMN="FIRST_NAME";
    public static final String SEQ_GEN_ALIAS="seq_gen_alias";
    public static final String SEQ_GEN_STRATEGY="uuid2";


    @Id
    @Column(name=Merchant.ID_COLUMN_NAME)
    @GeneratedValue(generator = Merchant.SEQ_GEN_ALIAS)
    @GenericGenerator(name=Merchant.SEQ_GEN_ALIAS,strategy = Merchant.SEQ_GEN_STRATEGY)
    private String id;

    private String merchantName;
    private String dateOfJoining;

    @OneToMany(mappedBy = "merchant",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MerchantReport> merchantReports= new HashSet<>();

    @OneToMany(mappedBy = "merchant1",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductInventory> productInventories= new HashSet<>();


}
