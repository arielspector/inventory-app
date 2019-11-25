package com.spector.inventoryapp.service;

import com.spector.inventoryapp.entities.InventoryItem;
import com.spector.inventoryapp.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryItem> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<InventoryItem> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public InventoryItem save(InventoryItem _item) {
        return inventoryRepository.save(_item);
    }

    @Override
    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }
}
