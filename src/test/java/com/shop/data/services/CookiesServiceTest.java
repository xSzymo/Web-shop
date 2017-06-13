package com.shop.data.services;


import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.Cookies;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CookiesServiceTest extends DataBaseTestConfiguration {
	@Autowired
	private CookiesService service;

	private LinkedList<Cookies> categories;

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
		Cookies actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.compareTwoCategories(service.findOne(actualCategory.getId())));
	}

	@Test
	public void saveOne() {
		Cookies actualCategory = categories.getFirst();

		service.save(actualCategory);

		assertTrue(actualCategory.compareTwoCategories(service.findOne(actualCategory.getId())));
	}

	@Test
	public void saveCollection() {
		LinkedList<Cookies> actualCategory = categories;

		service.save(actualCategory);

		actualCategory.forEach(
				x -> assertTrue(x.compareTwoCategories(service.findOne(x.getId())))
		);
	}

	@Test
	public void saveNull() {
		Cookies actualCategory = null;

		try {
			service.save(actualCategory);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void saveOneWithExistName() {
		categories.add(new Cookies("category " + 6, "category " + 6));
		categories.add(new Cookies("category " + 6, "category " + 6));
		service.save(categories);

		for (Cookies x : service.findAll()) {
			int numberOfSameObject = 0;
			for (Cookies x1 : service.findAll()) {
				if (x.getName().equals(x1.getName()))
					numberOfSameObject++;
				if (numberOfSameObject > 1)
					fail("can't save two cookies with same name");
			}
		}
	}

	@Test
	public void findOne() {
		service.save(categories.getFirst());

		Cookies actualCategory = service.findOne(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneWithNull() {
		Cookies actualCategory = null;

		try {
			service.findOne(actualCategory);
			service.findOneByName(actualCategory);
			service.findOne(null);
			service.findOneByName((String) null);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void findOneById() {
		service.save(categories.getFirst());

		Cookies actualCategory = service.findOne(categories.getFirst().getId());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByNameWithString() {
		service.save(categories.getFirst());

		Cookies actualCategory = service.findOneByName(categories.getFirst().getName());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByValueWithString() {
		service.save(categories.getFirst());

		Cookies actualCategory = service.findOneByValue(categories.getFirst().getValue());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByValueWithObject() {
		service.save(categories.getFirst());

		Cookies actualCategory = service.findOneByValue(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findOneByNameWithObject() {
		service.save(categories.getFirst());

		Cookies actualCategory = service.findOneByName(categories.getFirst());

		assertNotNull(actualCategory);
	}

	@Test
	public void findAll() {
		Iterable<Cookies> actualCategory = service.findAll();

		actualCategory.forEach(
				x -> assertNotNull(service.findOne(x.getId()))
		);
	}

	@Test
	public void delete() {
		Cookies actualCategory = categories.getFirst();

		service.save(actualCategory);
		service.delete(actualCategory);

		assertNull(service.findOne(actualCategory.getId()));
	}

	@Test
	public void deleteById() {
		Cookies actualCategory = categories.getFirst();

		service.save(actualCategory);
		service.delete(actualCategory.getId());

		assertNull(service.findOne(actualCategory.getId()));
	}

	@Test
	public void deleteCollection() {
		LinkedList<Cookies> actualCategory = categories;

		service.save(actualCategory);
		service.delete(actualCategory);

		actualCategory.forEach(
				x -> assertNull(service.findOne(x.getId()))
		);
	}

	public LinkedList<Cookies> createCategoriesCollection() {
		LinkedList<Cookies> categoriesToReturn = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			Cookies book = new Cookies("category " + i, "category " + i + 1);
			categoriesToReturn.add(book);
		}
//			categoriesToReturn.add(new Cookies("category " + 3));
		return categoriesToReturn;
	}


}