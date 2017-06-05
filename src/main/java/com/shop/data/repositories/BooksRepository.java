package com.shop.data.repositories;

import com.shop.data.tables.Book;
import com.shop.data.tables.Picture;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Book, Long> {
    Book findById(Long bookId);

    Book findByName(String name);

    void save(Picture picture);
}
