package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.repositories.OrdersRepository;
import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Order;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OrdersServiceTest {/*extends DataBaseTestConfiguration {
	@Autowired
	private OrdersService service;
	@Autowired
	private BooksService booksService;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private OrdersRepository repository;


	private LinkedList<Order> orders;
	private LinkedList<Book> books;
	private Category category;
	@Before
	public void setUp() {
		orders = createOrdersCollectionAndNewCategory();
	}

	@After
	public void afterEachTest() {
		service.delete(orders);
	}

	@Test
	public void saveOne() {
		Order actualBook = orders.getFirst();

		service.save(actualBook);

		assertTrue(actualBook.equals(service.findOne(actualBook.getId())));
	}

	@Test
	public void saveCollection() {
		LinkedList<Order> actualBook = orders;

		service.save(actualBook);

		actualBook.forEach(
				x ->
						assertTrue(x.equals(service.findOne(x.getId())))
		);
	}

	@Test
	public void saveNull() {
		Order actualAddress = null;

		try {
			service.save(actualAddress);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void findOne() {
		service.save(orders.getFirst());

		Order actualBook = service.findOne(orders.getFirst());

		assertNotNull(actualBook);
	}

	@Test
	public void findOneById() {
		service.save(orders.getFirst());

		Order actualBook = service.findOne(orders.getFirst().getId());

		assertNotNull(actualBook);
	}

	@Test
	public void findAll() {
		Iterable<Order> actualBooks = service.findAll();

		actualBooks.forEach(
				x -> {
					service.save(x);
					assertNotNull(service.findOne(x.getId()));
				}
		);
	}

	@Test
	public void delete() {
		Order actualBook = orders.getFirst();

		service.save(actualBook);
		service.delete(actualBook);

		System.out.println(service.findOne(actualBook.getId()));

		assertNull(service.findOne(actualBook.getId()));
	}

	@Test
	public void deleteById() {
		Order actualBook = orders.getFirst();

		service.save(actualBook);
		service.delete(actualBook);

		assertNotNull(usersRepository.findById(actualBook.getUser().getId()));
		assertNull(service.findOne(actualBook.getId()));
	}



	@Test
	public void deleteCollection() {
		LinkedList<Order> actualBook = orders;

		service.save(actualBook);
		service.delete(actualBook);

		actualBook.forEach(
				x -> assertNull(service.findOne(x.getId()))
		);
	}

	private LinkedList<Order> createOrdersCollectionAndNewCategory() {
		LinkedList<Order> booksToReturn = new LinkedList<>();
		User user = new User("Adam", "Adam", "Adam");
		usersRepository.save(user);
		for (int i = 0; i < 3; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(i%2);
			Order book = new Order(new BigDecimal("10" + i*100),
					Boolean.parseBoolean(sb.toString()));
			book.setBooks(createBooksCollectionAndNewCategory());
			book.setUser(user);
			booksToReturn.add(book);
		}
		return booksToReturn;
	}

	private LinkedList<Book> createBooksCollectionAndNewCategory() {
		LinkedList<Book> booksToReturn = new LinkedList<>();
		category = new Category("1253");
		categoriesService.save(category);
		for (int i = 0; i < 3; i++) {
			Book book = new Book("book" + i);
			book.setCategory(category);
			booksToReturn.add(book);
		}
		categoriesService.save(category);
		return booksToReturn;
	}*/
}
