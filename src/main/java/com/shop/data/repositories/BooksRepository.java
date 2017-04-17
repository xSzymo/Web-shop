package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Book;
import com.shop.data.tables.Picture;

public interface BooksRepository extends CrudRepository<Book, Long> {
	public Book findById(Long bookId);
	public Book findByName(String name);

	public void save(Picture picture);
}
