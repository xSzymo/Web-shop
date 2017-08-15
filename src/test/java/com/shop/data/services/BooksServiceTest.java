package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class BooksServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private BooksService service;
    @Autowired
    private CategoriesService categoriesService;

    private LinkedList<Book> books;
    private Category category;

    @Before
    public void setUp() {
        books = createBooksCollection();
    }

    @After
    public void afterEachTest() {
        service.delete(books);
        categoriesService.delete(category);
    }

    @Test
    public void saveOne() {
        Book book = books.getFirst();

        service.save(book);

        assertTrue(book.compareTwoBooks(service.findOne(book.getId())));
    }

    @Test
    public void saveCollection() {
        LinkedList<Book> books = this.books;

        service.save(books);

        books.forEach(
                x ->
                        assertTrue(x.compareTwoBooks(service.findOne(x.getId())))
        );
    }

    @Test
    public void saveNull() {
        try {
            service.save((Book) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void findOne() {
        service.save(books.getFirst());

        Book book = service.findOne(books.getFirst());

        assertNotNull(book);
    }

    @Test
    public void findOneById() {
        service.save(books.getFirst());

        Book book = service.findOne(books.getFirst().getId());

        assertNotNull(book);
    }

    @Test
    public void findAll() {
        service.save(this.books);
        Iterable<Book> books = this.books;

        books.forEach(
                x ->
                        assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void delete() {
        Book book = books.getFirst();

        service.save(book);
        service.delete(book);

        assertNull(service.findOne(book.getId()));
    }

    @Test
    public void deleteById() {
        Book book = books.getFirst();

        service.save(book);
        service.delete(book.getId());

        assertNull(service.findOne(book.getId()));
    }

    @Test
    public void deleteCollection() {
        LinkedList<Book> books = this.books;

        service.save(books);
        service.delete(books);

        books.forEach(
                x -> assertNull(service.findOne(x.getId()))
        );
    }

    public LinkedList<Book> createBooksCollection() {
        LinkedList<Book> booksToReturn = new LinkedList<>();
        category = new Category("123");
        for (int i = 0; i < 3; i++) {
            Book book = new Book("book" + i);
            booksToReturn.add(book);
            category.getBooks().add(book);
        }
        categoriesService.save(category);
        return booksToReturn;
    }
}
