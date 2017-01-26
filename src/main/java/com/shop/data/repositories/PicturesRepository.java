package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Pictures;
import com.shop.data.tables.Users;

public interface PicturesRepository extends CrudRepository<Pictures, Long> {
	public Pictures findByName(String name);
}
