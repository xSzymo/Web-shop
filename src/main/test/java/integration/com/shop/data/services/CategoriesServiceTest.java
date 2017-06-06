package integration.com.shop.data.services;

import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.services.CategoriesService;
import com.shop.data.tables.Category;
import integration.com.DataBaseTestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.*;


public class CategoriesServiceTest extends DataBaseTestConfiguration {
	@Autowired
	private CategoriesService service;
	@Autowired
	private CategoriesRepository categoriesRepository;

	private LinkedList<Category> categories;
	//private Category category;

	@Before
	public void setUp() {
		categories = createBooksCollectionAndNewCategory();
	}

	@After
	public void afterEachTest() {
		service.delete(categories);
		//categoriesRepository.delete(category);
	}

	@Test
	public void saveOne() {
		Category actualBook = categories.getFirst();

		service.save(actualBook);

		assertTrue(actualBook.compareTwoCategories(service.findOne(actualBook.getId())));
	}

	@Test
	public void saveCollection() {
		LinkedList<Category> actualBook = categories;

		service.save(actualBook);

		actualBook.forEach(
				x ->
						assertTrue(x.compareTwoCategories(service.findOne(x.getId())))
		);
	}

	@Test
	public void findOne() {
		service.save(categories.getFirst());

		Category actualBook = service.findOne(categories.getFirst());

		assertNotNull(actualBook);
	}

	@Test
	public void findOneById() {
		service.save(categories.getFirst());

		Category actualBook = service.findOne(categories.getFirst().getId());

		assertNotNull(actualBook);
	}

	@Test
	public void findAll() {
		Iterable<Category> actualBooks = service.findAll();

		actualBooks.forEach(
				x ->
						assertNotNull(service.findOne(x.getId()))
		);
	}

	@Test
	public void delete() {
		Category actualBook = categories.getFirst();

		service.save(actualBook);
		service.delete(actualBook);

		assertNull(service.findOne(actualBook.getId()));
	}

	@Test
	public void deleteById() {
		Category actualBook = categories.getFirst();

		service.save(actualBook);
		service.delete(actualBook.getId());

		assertNull(service.findOne(actualBook.getId()));
	}

	@Test
	public void deleteCollection() {
		LinkedList<Category> actualBook = categories;

		service.save(actualBook);
		service.delete(actualBook);

		actualBook.forEach(
				x -> assertNull(service.findOne(x.getId()))
		);
	}

	public LinkedList<Category> createBooksCollectionAndNewCategory() {
		LinkedList<Category> booksToReturn = new LinkedList<>();
		//category = new Category("123");
		//categoriesRepository.save(category);
		for (int i = 0; i < 3; i++) {
			Category book = new Category("book" + i);
			//book.setCategory(category);
			booksToReturn.add(book);
		}
		//categoriesRepository.save(category);
		return booksToReturn;
	}
}