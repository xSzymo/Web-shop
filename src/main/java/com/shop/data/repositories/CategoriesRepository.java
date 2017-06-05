package com.shop.data.repositories;

import com.shop.data.tables.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);

    Category findById(Long categoryId);
}
