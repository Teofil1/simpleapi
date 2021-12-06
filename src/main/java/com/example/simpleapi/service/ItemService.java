package com.example.simpleapi.service;


import com.example.simpleapi.exception.ItemNotFoundException;
import com.example.simpleapi.model.dto.ItemRequestDTO;
import com.example.simpleapi.model.dto.ItemResponseDTO;
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

    public ItemResponseDTO addItem(ItemRequestDTO itemRequestDTO) {
        /*w danej implementacji id jest generowane po stronie serwera,
        jeśli by id było przekazywane od klienta to w tym miejscu należało by najpierw
        wyszukać z bazy czy istnieje już element o podanym id. Jeśli tak, to wyrzucić
        odpowiedni wyjątek.*/

        Item newItem = new Item(UUID.randomUUID().toString(), itemRequestDTO.getName());
        Item savedItem = itemRepository.save(newItem);
        return new ItemResponseDTO(savedItem.getId(), savedItem.getName());
    }


    public ItemResponseDTO getItem(String id) {
        Item item = itemRepository.getById(id);
        if (item == null){
            throw new ItemNotFoundException(id);
        }
        return new ItemResponseDTO(item.getId(), item.getName());
    }
}
