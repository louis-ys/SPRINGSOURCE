package com.example.mart.repository;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.CategoryItem;
import com.example.mart.entity.Delivery;
import com.example.mart.entity.Item;
import com.example.mart.entity.Member;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {

}
