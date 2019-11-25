package com.spector.inventoryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="inventory")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "item_id", updatable = false, nullable = false)
    private long id;

    @Column(name="item_name", unique=true, length = 32, nullable = false)
    private String name;

    @Column(name="item_amount", nullable = false)
    private int amount;

    @Column(name="item_inventory_code", unique=true, length = 32, nullable = false)
    private String code;


}
