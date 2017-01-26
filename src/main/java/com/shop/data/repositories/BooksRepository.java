package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Books;
import com.shop.data.tables.Pictures;

public interface BooksRepository extends CrudRepository<Books, Long> {
	public Books findById(Long bookId);
	public Books findByName(String name);

	public void save(Pictures picture);
}
