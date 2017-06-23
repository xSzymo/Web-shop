package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
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
	public void BeforeEachTest() {
		categories = createCategoriesCollection();
	}

	@After
	public void afterEachTest() {
		service.delete(categories);
	}

	@Test
	public void save() {
		Category actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.compareTwoCategories(service.findOne(actualCategory.getId())));
	}

	@Test
	public void saveOne() {
		Category actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.compareTwoCategories(service.findOne(actualCategory.getId())));
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
	public void saveNull() {
		Category actualCategory = null;

		try {
			service.save(actualCategory);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void saveOneWithExistName() {
		categories.add(new Category("category " + 3));
		categories.add(new Category("category " + 3));
		service.save(categories);

		for (Category x : service.findAll()) {
			int numberOfSameObject = 0;
			for (Category x1 : service.findAll()) {
				if (x.getName().equals(x1.getName()))
					numberOfSameObject++;
				if (numberOfSameObject > 1)
					fail("can't save two categories with same name");
			}
		}
	}

	@Test
	public void updateCategoryWithSetBooks() {
		Category actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.compareTwoCategories(service.findOne(actualCategory.getName())));


		Category categoryToUpdate = service.findOne(actualCategory.getName());

		LinkedList<Book> books = new LinkedList<>();
		books.add(new Book("avcx"));
		books.add(new Book("avcx1"));

		categoryToUpdate.setBooks(books);
		service.save(categoryToUpdate);

		Category one = service.findOne(categoryToUpdate.getName());
		assertTrue(categoryToUpdate.equals(one));
	}

	@Test
	public void updateCategoryWithAddBooks() {
		Category actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.compareTwoCategories(service.findOne(actualCategory.getName())));


		Category categoryToUpdate = service.findOne(actualCategory.getName());

		LinkedList<Book> books = new LinkedList<>();
		books.add(new Book("avcx"));
		books.add(new Book("avcx1"));

		categoryToUpdate.getBooks().addAll(books);
		service.save(categoryToUpdate);

		assertTrue(categoryToUpdate.compareTwoCategories(service.findOne(actualCategory.getName())));
	}

	@Test
	public void findOne() {
		service.save(categories.getFirst());

		Category actualCategory = service.findOne(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneWithNull() {
		Category actualCategory = null;

		try {
			service.findOne(actualCategory);
			service.findOneByName(actualCategory);
			service.findOne((Category) null);
			service.findOneByName((String) null);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void findOneById() {
		service.save(categories.getFirst());

		Category actualCategory = service.findOne(categories.getFirst().getId());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByNameWithString() {
		service.save(categories.getFirst());

		Category actualCategory = service.findOneByName(categories.getFirst().getName());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByNameWithObject() {
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
		return categoriesToReturn;
	}
}