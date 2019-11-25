package com.spector.inventoryapp.rest;


import com.spector.inventoryapp.entities.InventoryItem;
import com.spector.inventoryapp.service.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;


/**
 *
 *  REST Controller Class for Inventory
 *
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="inventory")
public class InventoryController {

    private static Logger logger = LogManager.getLogger(InventoryController.class.getName());

    private final InventoryServiceImpl inventoryService;

    /**
     * Get all inventory items
     */
    @GetMapping(path= "/list", produces = "application/json")
    public ResponseEntity<List<InventoryItem>> list() {
        return ResponseEntity.ok(this.inventoryService.findAll());
    }

    /**
     * Get inventory item by "id"
     */
    @GetMapping(path = "/single/{id}", produces = "application/json")
    public ResponseEntity<InventoryItem> single(@PathVariable(value = "id") Long _id) {
        return this.inventoryService.findById(_id)
                .map(inventory_manager -> ResponseEntity.ok().body(inventory_manager))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new inventory item
     */
    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<InventoryItem> create(@RequestBody @Valid InventoryItem inventoryItem) {
        try {
            return ResponseEntity.ok(inventoryService.save(inventoryItem));
        } catch (Exception e) {
            logger.warn("Caught exception when adding new inventory item: " + e.toString());
            return new ResponseEntity<>((InventoryItem) null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update existing inventory item
     */
    @PutMapping(path = "/update/{id}", produces = "application/json")
    public ResponseEntity<InventoryItem> update(@PathVariable(value="id") Long id, @RequestBody @Valid InventoryItem inventoryItem) {
        try {
            return inventoryService.findById(id)
                    .map(record -> {
                        record.setName(inventoryItem.getName());
                        record.setCode(inventoryItem.getCode());
                        record.setAmount(inventoryItem.getAmount());
                        InventoryItem updated = inventoryService.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        } catch(Exception e) {
            logger.warn("Caught exception when updating inventory item. ID: " + id + ": " + e.toString());
            return new ResponseEntity<>((InventoryItem) null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete inventory item
     */
    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            inventoryService.deleteById(id);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            logger.warn("Caught exception when deleting inventory item. ID: " + id + e.toString());
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deposit quantity of a specific existing inventory item
     */
    @PutMapping(path = "/deposit/{id}", produces = "application/json")
    public ResponseEntity<InventoryItem> deposit(@PathVariable(value="id") Long id, @RequestParam @Valid int amount) {
        try {
            return inventoryService.findById(id)
                    .map(record -> {
                        record.setAmount(record.getAmount() + amount);
                        InventoryItem updated = inventoryService.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        } catch(Exception e) {
            logger.warn("Caught exception when depositing quantity of a specific inventory item. ID: " + id + ": " + e.toString());
            return new ResponseEntity<>((InventoryItem) null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Withdrawal quantity of a specific existing inventory item
     */
    @PutMapping(path = "/withdrawal/{id}", produces = "application/json")
    public ResponseEntity<InventoryItem> withdrawal(@PathVariable(value="id") Long id, @RequestParam @Valid int amount) {
        try {
            return inventoryService.findById(id)
                    .map(record -> {
                        if(record.getAmount() >= amount) record.setAmount(record.getAmount() - amount);
                        InventoryItem updated = inventoryService.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        } catch(Exception e) {
            logger.warn("Caught exception when withdrawal quantity of a specific inventory item. ID: " + id + ": " + e.toString());
            return new ResponseEntity<>((InventoryItem) null, HttpStatus.BAD_REQUEST);
        }
    }
}
