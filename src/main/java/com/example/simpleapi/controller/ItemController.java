package com.example.simpleapi.controller;

import com.example.simpleapi.exception.ItemNotFoundException;
import com.example.simpleapi.model.dto.ItemRequestDTO;
import com.example.simpleapi.model.dto.ItemResponseDTO;
import com.example.simpleapi.model.entity.Item;
import com.example.simpleapi.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    ResponseEntity<ItemResponseDTO> addItem(@RequestBody ItemRequestDTO itemRequestDTO) {
        return ResponseEntity.ok(itemService.addItem(itemRequestDTO));
    }


    @GetMapping("/items/{id}")
    ResponseEntity<?> getItem(@PathVariable String id) {
        try {
            return ResponseEntity.ok(itemService.getItem(id));
        } catch (ItemNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
