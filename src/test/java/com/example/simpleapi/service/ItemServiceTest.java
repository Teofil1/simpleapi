package com.example.simpleapi.service;

import com.example.simpleapi.exception.ItemNotFoundException;
import com.example.simpleapi.model.dto.ItemResponseDTO;
import com.example.simpleapi.model.entity.Item;
import com.example.simpleapi.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    private String itemId;
    private Item expItem;

    @Before
    public void setUp() {
        itemService = new ItemService(itemRepository);
        itemId = UUID.randomUUID().toString();
    }

    @Test
    public void shouldReturnItemWhenExist() {
        expItem = new Item(itemId, "item");
        when(itemRepository.getById(itemId)).thenReturn(expItem);
        ItemResponseDTO itemResponseDTO = itemService.getItem(itemId);
        assertEquals(expItem.getId(), itemResponseDTO.getId());
        assertEquals(expItem.getName(), itemResponseDTO.getName());
    }

    @Test
    public void shouldThrowItemNotFoundWhenDoesNotExist() {
        when(itemRepository.getById(itemId)).thenReturn(null);
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> {
            itemService.getItem(itemId);
        });
        assertEquals("Could not find item with id " + itemId, exception.getMessage());
    }

}
