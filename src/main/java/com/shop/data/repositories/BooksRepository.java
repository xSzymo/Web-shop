package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Books;

public interface BooksRepository extends CrudRepository<Books, Long> {

}
