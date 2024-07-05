package com.pdk.inventoryservice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pdk.inventoryservice.Model.Inventory;
import com.pdk.inventoryservice.Service.InventoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/skuCode")
    @ResponseStatus(HttpStatus.OK)
    public boolean addInventory(@PathVariable String skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("/name")
    public String getAllInventories() {
        return "My name is Pranav";
    }

}
