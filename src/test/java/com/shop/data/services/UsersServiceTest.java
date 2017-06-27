package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.repositories.BooksRepository;
import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.repositories.OrdersRepository;
import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Order;
import com.shop.data.tables.User;
import org.dom4j.io.SAXEventRecorder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UsersServiceTest extends DataBaseTestConfiguration {
	@Autowired
	private UsersService service;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private CategoriesRepository categoriesRepository;
	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private BooksService booksService;

	private LinkedList<User> categories;


	@Before
	public void BeforeEachTest() {
		categories = createCategoriesCollection();
	}

	//delete
	@After
	public void afterEachTest() {
//		categories.forEach(
//				x ->    x.getOrders().forEach(
//							x1 -> x1.getBooks().forEach(
//									x2 -> booksService.delete(x2)
//								)
//						)
//		);
//
//		categoriesRepository.deleteAll();
//		usersRepository.deleteAll();
//		ordersRepository.deleteAll();
	}

	@Test
	public void test() {
		User user = new User("login", "password", "email");
		Order order = new Order(new BigDecimal("1"), false);
		Category category = new Category("category");
		Book book = new Book("book");
		book.setCategory(category);
		order.getBooks().add(book);
		user.getOrders().add(order);

		service.save(user);

		assertTrue(user.equals(service.findByLogin(user)));
	}

	@Test
	public void save() {
		User actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.equals(service.findOne(actualCategory.getId())));
	}

	@Test
	public void saveCollection() {
		LinkedList<User> actualCategory = categories;

		service.save(actualCategory);

		actualCategory.forEach(
				x -> assertTrue(x.equals(service.findOne(x.getId())))
		);
	}

	@Test
	public void saveNull() {
		User actualCategory = null;

		try {
			service.save(actualCategory);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void saveOneWithExistLogin() {
		categories.add(new User("category " + 3, "", ""));
		categories.add(new User("category " + 3, "", ""));
		service.save(categories);

		for (User x : service.findAll()) {
			int numberOfSameObject = 0;
			for (User x1 : service.findAll()) {
				if (x.getLogin().equals(x1.getLogin()))
					numberOfSameObject++;
				if (numberOfSameObject > 1)
					fail("can't save two categories with same name");
			}
		}
	}

//	@Test
//	public void updateCategoryWithSetBooks() {
//		User actualCategory = categories.getFirst();
//
//		service.save(actualCategory);
//
//		assertTrue(actualCategory.equals(service.findOne(actualCategory.getName())));
//
//
//		User categoryToUpdate = service.findOne(actualCategory.getName());
//
//		LinkedList<Book> books = new LinkedList<>();
//		books.add(new Book("avcx"));
//		books.add(new Book("avcx1"));
//
//		categoryToUpdate.(books);
//		service.save(categoryToUpdate);
//
//		Category one = service.findOne(categoryToUpdate.getName());
//		assertTrue(categoryToUpdate.equals(one));
//	}
//
//	@Test
//	public void updateCategoryWithAddBooks() {
//		User actualCategory = categories.getFirst();
//
//		service.save(actualCategory);
//
//		assertTrue(actualCategory.equals(service.findOne(actualCategory.getName())));
//
//
//		User categoryToUpdate = service.findOne(actualCategory.getName());
//
//		LinkedList<Book> books = new LinkedList<>();
//		books.add(new Book("avcx"));
//		books.add(new Book("avcx1"));
//
//		categoryToUpdate.getBooks().addAll(books);
//		service.save(categoryToUpdate);
//
//		assertTrue(categoryToUpdate.equals(service.findOne(actualCategory.getName())));
//	}

	@Test
	public void findOne() {
		service.save(categories.getFirst());

		User actualCategory = service.findOne(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneWithNull() {
		Category actualCategory = null;

		try {
//			service.findOne(actualCategory);
//			service.findByLogin(actualCategory);
//			service.findOne((Category) null);
			service.findByLogin((String) null);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void findOneById() {
		service.save(categories.getFirst());

		User actualCategory = service.findOne(categories.getFirst().getId());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByNameWithString() {
		service.save(categories.getFirst());

		User actualCategory = service.findByLogin(categories.getFirst().getLogin());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByNameWithObject() {
		service.save(categories.getFirst());

		User actualCategory = service.findByLogin(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findAll() {
		Iterable<User> actualCategory = service.findAll();

		actualCategory.forEach(
				x -> assertNotNull(service.findOne(x.getId()))
		);
	}

	@Test
	public void delete() {
		User actualCategory = categories.getFirst();

		service.save(actualCategory);
		service.delete(actualCategory);

		assertNull(service.findOne(actualCategory.getId()));
	}

	@Test
	public void deleteById() {
		User actualCategory = categories.getFirst();

		service.save(actualCategory);
		service.delete(actualCategory.getId());

		assertNull(service.findOne(actualCategory.getId()));
		actualCategory.getOrders().forEach(
				x -> assertNull(ordersService.findOne(x)));
		actualCategory.getOrders().forEach(
				x -> x.getBooks().forEach(
						x1 -> {
							assertNotNull(booksRepository.findByName(x1.getName()));
							assertNotNull(categoriesService.findOneByName(x1.getCategory().getName()));
						}
				)
		);
	}

	@Test
	public void deleteCollection() {
		LinkedList<User> actualCategory = categories;

		service.save(actualCategory);
		service.delete(actualCategory);

		actualCategory.forEach(
				x -> assertNull(service.findOne(x.getId()))
		);
	}

	public LinkedList<User> createCategoriesCollection() {
		LinkedList<User> categoriesToReturn = new LinkedList<>();

		for(int i = 0; i < 3; i++) {
			User user = new User("login" + i, "password", "email" + i);
			Order order = new Order(new BigDecimal("1"), false);
			Category category = new Category("category" + i);
			Book book = new Book("book");
			book.setCategory(category);
			order.getBooks().add(book);
			user.getOrders().add(order);

			categoriesToReturn.add(user);
		}
		return categoriesToReturn;
	}
}