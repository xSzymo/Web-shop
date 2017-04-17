package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.Category;

public interface CategoriesRepository extends CrudRepository<Category, Long> {
	public Category findByName(String name);
	public Category findById(Long categoryId);
}
