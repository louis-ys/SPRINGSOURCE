package com.example.mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.Item;
import com.example.mart.entity.Member;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
