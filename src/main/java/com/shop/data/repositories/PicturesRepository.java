package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Pictures;

public interface PicturesRepository extends CrudRepository<Pictures, Long> {
	public Pictures findById(Long pictureId);
	public Pictures findByName(String name);
}
