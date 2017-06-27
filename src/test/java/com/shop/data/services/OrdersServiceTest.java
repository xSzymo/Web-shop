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

public class OrdersServiceTest extends DataBaseTestConfiguration {
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
		orders = createBooksCollectionAndNewCategory();
	}

	@After
	public void afterEachTest() {
		for(int i = 0; i < 3; i++)
			categoriesService.delete("category" + i);
		service.delete(orders);
	}

	@Test
	public void saveOne() {
		Order order = orders.getFirst();

		service.save(order);

		assertTrue(order.equals(service.findOne(order.getId())));
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


	public LinkedList<Order> createBooksCollectionAndNewCategory() {
		LinkedList<Order> booksToReturn = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			Order order = new Order(new BigDecimal("123" + i*100), false);
			order.setUser(new User("123" + i*100, "123", "123" + i*100));

			LinkedList<Book> books = new LinkedList<>();
			Book book = new Book("123" + i);
			Category category = new Category("category" + i);
			book.setCategory(category);
			books.add(book);
			book.getOrders().add(order);
			category.getBooks().add(book);
			order.setBooks(books);

			categoriesService.save(category);
			booksToReturn.add(order);
		}
		return booksToReturn;
	}
}
