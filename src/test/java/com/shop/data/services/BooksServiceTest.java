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
		books = createBooksCollectionAndNewCategory();
	}

	@After
	public void afterEachTest() {
		service.delete(books);
		categoriesService.delete(category);
	}

	@Test
	public void saveOne() {
		Book actualBook = books.getFirst();

		service.save(actualBook);

		assertTrue(actualBook.compareTwoBooks(service.findOne(actualBook.getId())));
	}

	@Test
	public void saveCollection() {
		LinkedList<Book> actualBook = books;

		service.save(actualBook);

		actualBook.forEach(
				x ->
						assertTrue(x.compareTwoBooks(service.findOne(x.getId())))
		);
	}

	@Test
	public void saveNull() {
		Book actualAddress = null;

		try {
			service.save(actualAddress);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void findOne() {
		service.save(books.getFirst());

		Book actualBook = service.findOne(books.getFirst());

		assertNotNull(actualBook);
	}

	@Test
	public void findOneById() {
		service.save(books.getFirst());

		Book actualBook = service.findOne(books.getFirst().getId());

		assertNotNull(actualBook);
	}

	@Test
	public void findAll() {
		Iterable<Book> actualBooks = service.findAll();

		actualBooks.forEach(
				x ->
						assertNotNull(service.findOne(x.getId()))
		);
	}

	@Test
	public void delete() {
		Book actualBook = books.getFirst();

		service.save(actualBook);
		service.delete(actualBook);

		assertNull(service.findOne(actualBook.getId()));
	}

	@Test
	public void deleteById() {
		Book actualBook = books.getFirst();

		service.save(actualBook);
		service.delete(actualBook.getId());

		assertNull(service.findOne(actualBook.getId()));
	}

	@Test
	public void deleteCollection() {
		LinkedList<Book> actualBook = books;

		service.save(actualBook);
		service.delete(actualBook);

		actualBook.forEach(
				x -> assertNull(service.findOne(x.getId()))
		);
	}

	//TODO - getBooks bug - check for fetchtype
	public LinkedList<Book> createBooksCollectionAndNewCategory() {
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
