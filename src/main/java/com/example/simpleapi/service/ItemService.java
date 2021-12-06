package com.example.simpleapi.service;


import com.example.simpleapi.exception.ItemNotFoundException;
import com.example.simpleapi.model.dto.ItemRequestDTO;
import com.example.simpleapi.model.entity.Item;
import com.example.simpleapi.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(ItemRequestDTO itemRequestDTO) {
        Item newItem = new Item(UUID.randomUUID().toString(), itemRequestDTO.getName());
        return itemRepository.save(newItem);
    }


    public Item getItem(String id) throws ItemNotFoundException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
}
