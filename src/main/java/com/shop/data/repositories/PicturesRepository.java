package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Picture;

public interface PicturesRepository extends CrudRepository<Picture, Long> {
	public Picture findById(Long pictureId);
	public Picture findByName(String name);
}
