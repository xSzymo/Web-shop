package com.shop.data.repositories;

import com.shop.data.tables.Book;
import com.shop.data.tables.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BooksRepository extends CrudRepository<Book, Long> {
    Book findById(Long bookId);

    Book findByName(String name);

    Book save(Book book);

    void save(Collection<Book> books);
}
