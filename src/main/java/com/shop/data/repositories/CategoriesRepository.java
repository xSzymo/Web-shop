package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Categories;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {

}
