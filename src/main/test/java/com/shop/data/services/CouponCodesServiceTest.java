package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.CouponCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CouponCodesServiceTest extends DataBaseTestConfiguration {
		@Autowired
		private CouponCodesService service;

		private LinkedList<CouponCode> categories;

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
			CouponCode actualCategory = categories.getFirst();

			service.save(actualCategory);

			assertTrue(actualCategory.compareTwoCouponCodes(service.findOne(actualCategory.getId())));
		}

		@Test
		public void saveOne() {
			CouponCode actualCategory = categories.getFirst();

			service.save(actualCategory);

			assertTrue(actualCategory.compareTwoCouponCodes(service.findOne(actualCategory.getId())));
		}

		@Test
		public void saveCollection() {
			LinkedList<CouponCode> actualCategory = categories;

			service.save(actualCategory);

			actualCategory.forEach(
					x -> assertTrue(x.compareTwoCouponCodes(service.findOne(x.getId())))
			);
		}

		@Test
		public void saveNull() {
			CouponCode actualCategory = null;

			try {
				service.save(actualCategory);
			} catch (Exception e) {
				assertNull(e);
			}
		}

		@Test
		public void saveOneWithExistName() {
			categories.add(new CouponCode(2D , "category" ));
			categories.add(new CouponCode(2D, "category"));
			service.save(categories);

			for (CouponCode x : service.findAll()) {
				int numberOfSameObject = 0;
				for (CouponCode x1 : service.findAll()) {
					if (x.getCode().equals(x1.getCode()))
						numberOfSameObject++;
					if (numberOfSameObject > 1)
						fail("can't save two categories with same name");
				}
			}
		}

		@Test
		public void findOne() {
			service.save(categories.getFirst());

			CouponCode actualCategory = service.findOne(categories.getFirst());

			assertNotNull(actualCategory);
		}

		@Test
		public void findOneWithNull() {
			CouponCode actualCategory = null;

			try {
				service.findOne(actualCategory);
				service.findOneByCode(actualCategory);
				service.findOne(null);
				service.findOneByCode((String) null);
			} catch (Exception e) {
				assertNull(e);
			}
		}

		@Test
		public void findOneById() {
			service.save(categories.getFirst());

			CouponCode actualCategory = service.findOne(categories.getFirst().getId());

			assertNotNull(actualCategory);
		}

		@Test
		public void findOneByNameWithString() {
			service.save(categories.getFirst());

			CouponCode actualCategory = service.findOneByCode(categories.getFirst().getCode());

			assertNotNull(actualCategory);
		}

		@Test
		public void findOneByNameWithObject() {
			service.save(categories.getFirst());

			CouponCode actualCategory = service.findOneByCode(categories.getFirst());

			assertNotNull(actualCategory);
		}

		@Test
		public void findAll() {
			Iterable<CouponCode> actualCategory = service.findAll();

			actualCategory.forEach(
					x -> assertNotNull(service.findOne(x.getId()))
			);
		}

		@Test
		public void delete() {
			CouponCode actualCategory = categories.getFirst();

			service.save(actualCategory);
			service.delete(actualCategory);

			assertNull(service.findOne(actualCategory.getId()));
		}

		@Test
		public void deleteById() {
			CouponCode actualCategory = categories.getFirst();

			service.save(actualCategory);
			service.delete(actualCategory.getId());

			assertNull(service.findOne(actualCategory.getId()));
		}

		@Test
		public void deleteCollection() {
			LinkedList<CouponCode> actualCategory = categories;

			service.save(actualCategory);
			service.delete(actualCategory);

			actualCategory.forEach(
					x -> assertNull(service.findOne(x.getId()))
			);
		}

		public LinkedList<CouponCode> createCategoriesCollection() {
			LinkedList<CouponCode> categoriesToReturn = new LinkedList<>();
			for (int i = 0; i < 3; i++) {
				CouponCode book = new CouponCode(3D, "category " + i);
				categoriesToReturn.add(book);
			}
			//categoriesToReturn.add(new CouponCode("category " + 3));
			return categoriesToReturn;
		}
}


