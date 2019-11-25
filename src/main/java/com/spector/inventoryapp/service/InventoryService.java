package com.spector.inventoryapp.service;

import com.spector.inventoryapp.entities.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    List<InventoryItem> findAll();

    Optional<InventoryItem> findById(Long id);

    InventoryItem save(InventoryItem _item);

    void deleteById(Long id);
}
