package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.UserRole;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {
	public Categories findByName(String name);
	public Categories findById(Long categoryId);

}
