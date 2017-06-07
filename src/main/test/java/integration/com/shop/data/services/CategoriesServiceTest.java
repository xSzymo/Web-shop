package integration.com.shop.data.services;

import com.shop.data.services.CategoriesService;
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
import static org.junit.Assert.fail;


public class CategoriesServiceTest extends DataBaseTestConfiguration {
	@Autowired
	private CategoriesService service;

	private LinkedList<Category> categories;

	@Before
	public void setUp() {
		categories = createCategoriesCollection();
	}

	@After
	public void afterEachTest() {
		service.delete(categories);
	}

	@Test
	public void saveOne() {
		Category actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.compareTwoCategories(service.findOne(actualCategory.getId())));
	}

	@Test
	public void saveOneWithExistName() {
		service.save(categories);

		for (Category x : service.findAll()) {
			int a = 0;
			for (Category x1 : service.findAll()) {
				if (x.getName().equals(x1.getName()))
					a++;
				if (a > 1)
					fail("can't save two categories with same name");
			}
		}
	}

	@Test
	public void saveCollection() {
		LinkedList<Category> actualCategory = categories;

		service.save(actualCategory);

		actualCategory.forEach(
				x -> assertTrue(x.compareTwoCategories(service.findOne(x.getId())))
		);
	}

	@Test
	public void findOne() {
		service.save(categories.getFirst());

		Category actualCategory = service.findOne(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneById() {
		service.save(categories.getFirst());

		Category actualCategory = service.findOne(categories.getFirst().getId());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByName() {
		service.save(categories.getFirst());

		Category actualCategory = service.findOneByName(categories.getFirst().getName());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByName1() {
		service.save(categories.getFirst());

		Category actualCategory = service.findOneByName(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findAll() {
		Iterable<Category> actualCategory = service.findAll();

		actualCategory.forEach(
				x -> assertNotNull(service.findOne(x.getId()))
		);
	}

	@Test
	public void delete() {
		Category actualCategory = categories.getFirst();

		service.save(actualCategory);
		service.delete(actualCategory);

		assertNull(service.findOne(actualCategory.getId()));
	}

	@Test
	public void deleteById() {
		Category actualCategory = categories.getFirst();

		service.save(actualCategory);
		service.delete(actualCategory.getId());

		assertNull(service.findOne(actualCategory.getId()));
	}

	@Test
	public void deleteCollection() {
		LinkedList<Category> actualCategory = categories;

		service.save(actualCategory);
		service.delete(actualCategory);

		actualCategory.forEach(
				x -> assertNull(service.findOne(x.getId()))
		);
	}

	public LinkedList<Category> createCategoriesCollection() {
		LinkedList<Category> categoriesToReturn = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			Category book = new Category("category " + i);
			categoriesToReturn.add(book);
		}
		categoriesToReturn.add(new Category("category " + 3));
		categoriesToReturn.add(new Category("category " + 3));
		return categoriesToReturn;
	}
}