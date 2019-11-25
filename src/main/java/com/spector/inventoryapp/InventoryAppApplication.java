package com.spector.inventoryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class InventoryAppApplication {

    private static final Logger logger = LogManager.getLogger(InventoryAppApplication.class.getName());

    public static void main(String[] args) {

        logger.warn("Java Home    :: "+System.getProperty("java.home"));
        logger.warn("Java Version ::"+System.getProperty("java.version"));
        logger.warn("Working Dir  :: "+System.getProperty("user.dir"));

        SpringApplication.run(InventoryAppApplication.class, args);
    }

}
