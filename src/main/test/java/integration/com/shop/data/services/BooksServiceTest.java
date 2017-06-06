package integration.com.shop.data.services;

import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.services.BooksService;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import integration.com.DataBaseTestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BooksServiceTest extends DataBaseTestConfiguration {
	@Autowired
	private BooksService service;
	@Autowired
	private CategoriesRepository categoriesRepository;

	private LinkedList<Book> books;
	private Category category;

	@Before
	public void setUp() {
		books = createBooksCollectionAndNewCategory();
	}

	@After
	public void afterEachTest() {
		service.delete(books);
		categoriesRepository.delete(category);
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

	public LinkedList<Book> createBooksCollectionAndNewCategory() {
		LinkedList<Book> booksToReturn = new LinkedList<>();
		category = new Category("123");
		categoriesRepository.save(category);
		for (int i = 0; i < 3; i++) {
			Book book = new Book("book" + i);
			book.setCategory(category);
			booksToReturn.add(book);
		}
		categoriesRepository.save(category);
		return booksToReturn;
	}
}
