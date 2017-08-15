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


public class CategoriesServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private CategoriesService service;
    @Autowired
    private BooksService booksService;

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
        Category category = categories.getFirst();

        service.save(category);

        checker(category);
    }

    @Test
    public void saveOne() {
        Category category = categories.getFirst();

        service.save(category);

        checker(category);
    }

    @Test
    public void saveCollection() {
        LinkedList<Category> categories = this.categories;

        service.save(categories);

        categories.forEach(
                x -> checker(x)
        );
    }

    @Test
    public void saveNull() {
        try {
            service.save((Category) null);
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
        Category category = categories.getFirst();

        service.save(category);

        assertTrue(category.equals(service.findOne(category.getName())));


        Category categoryToUpdate = service.findOne(category.getName());

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
        Category category = categories.getFirst();

        service.save(category);

        assertTrue(category.equals(service.findOne(category.getName())));


        Category categoryToUpdate = service.findOne(category.getName());

        LinkedList<Book> books = new LinkedList<>();
        books.add(new Book("avcx"));
        books.add(new Book("avcx1"));

        categoryToUpdate.getBooks().addAll(books);
        service.save(categoryToUpdate);

        assertTrue(categoryToUpdate.equals(service.findOne(category.getName())));
    }

    @Test
    public void findOne() {
        service.save(categories.getFirst());

        Category category = service.findOne(categories.getFirst());

        assertNotNull(category);
    }

    @Test
    public void findOneWithNull() {
        try {
            service.findOne((Category) null);
            service.findOneByName((Category) null);
            service.findOne((Category) null);
            service.findOneByName((String) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void findOneById() {
        service.save(categories.getFirst());

        Category category = service.findOne(categories.getFirst().getId());

        assertNotNull(category);
    }

    @Test
    public void findOneByNameWithString() {
        service.save(categories.getFirst());

        Category category = service.findOneByName(categories.getFirst().getName());

        assertNotNull(category);
    }

    @Test
    public void findOneByNameWithObject() {
        service.save(categories.getFirst());

        Category category = service.findOneByName(categories.getFirst());

        assertNotNull(category);
    }

    @Test
    public void findAll() {
        Iterable<Category> categories = service.findAll();

        categories.forEach(
                x -> assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void delete() {
        Category category = categories.getFirst();

        service.save(category);
        service.delete(category);

        assertNull(service.findOne(category.getId()));
    }

    @Test
    public void deleteById() {
        Category category = categories.getFirst();

        service.save(category);
        service.delete(category.getId());

        assertNull(service.findOne(category.getId()));
    }

    @Test
    public void deleteCollection() {
        LinkedList<Category> categories = this.categories;

        service.save(categories);
        service.delete(categories);

        categories.forEach(
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

    private void checker(Category category) {
        category.getBooks().forEach(
                x -> assertNotNull(booksService.findOne(x))
        );

        assertTrue(category.equals(service.findOne(category.getId())));
    }
}