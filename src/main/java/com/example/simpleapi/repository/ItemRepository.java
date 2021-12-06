package com.example.simpleapi.repository;

import com.example.simpleapi.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, String> {

}
