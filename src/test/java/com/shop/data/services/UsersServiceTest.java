package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.repositories.*;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Order;
import com.shop.data.tables.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class UsersServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private UsersService service;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PicturesRepository picturesRepository;
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

    private LinkedList<User> users;

    private LinkedList<Category> categories = new LinkedList<>();


    @Before
    public void BeforeEachTest() {
        users = createUsersCollection();
    }

    @After
    public void afterEachTest() {

        try {
            usersRepository.deleteAll();
            ordersRepository.deleteAll();
            picturesRepository.deleteAll();
            booksRepository.deleteAll();
            categoriesService.delete(categories);
            categoriesService.delete("category0");
            categoriesService.delete("category1");
            categoriesService.delete("category2");
            categoriesRepository.deleteAll();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    @Test
    public void save() {
        User user = users.getFirst();

        service.save(user);

        assertTrue(user.equals(service.findOne(user.getId())));
    }


    @Test
    public void saveWithOrdersCollection() {
        User user = users.getFirst();

        service.save(user);

        ordersService.save(createOrdersCollection(user));

        assertTrue(user.equals(service.findOne(user.getId())));
    }

    @Test
    public void saveCollection() {
        LinkedList<User> user = users;

        service.save(user);

        user.forEach(
                x -> assertTrue(x.equals(service.findOne(x.getId())))
        );
    }

    @Test
    public void saveNull() {
        try {
            service.save((User) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void saveOneWithExistLogin() {
        users.add(new User("category " + 3, "", ""));
        users.add(new User("category " + 3, "", ""));
        service.save(users);

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

    @Test
    public void findOne() {
        service.save(users.getFirst());

        User user = service.findOne(users.getFirst());

        assertNotNull(user);
    }

    @Test
    public void findOneWithOrdersCollection() {
        User user = users.getFirst();

        service.save(user);

        ordersService.save(createOrdersCollection(user));

        assertNotNull(user);
    }

    @Test
    public void findOneWithNull() {
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
        service.save(users.getFirst());

        User user = service.findOne(users.getFirst().getId());

        assertNotNull(user);
    }

    @Test
    public void findOneByIdWithOrdersCollection() {
        service.save(users.getFirst());

        User user = service.findOne(users.getFirst().getId());

        ordersService.save(createOrdersCollection(user));

        assertNotNull(user);
    }

    @Test
    public void findOneByNameWithString() {
        service.save(users.getFirst());

        User user = service.findByLogin(users.getFirst().getLogin());

        assertNotNull(user);
    }

    @Test
    public void findOneByNameWithStringWithOrdersCollection() {
        service.save(users.getFirst());

        User user = service.findByLogin(users.getFirst().getLogin());

        ordersService.save(createOrdersCollection(user));

        assertNotNull(user);
    }

    @Test
    public void findOneByNameWithObject() {
        service.save(users.getFirst());

        User user = service.findByLogin(users.getFirst());

        assertNotNull(user);
    }

    @Test
    public void findOneByNameWithObjectWithOrdersCollection() {
        service.save(users.getFirst());

        User user = service.findByLogin(users.getFirst());

        ordersService.save(createOrdersCollection(user));

        assertNotNull(user);
    }

    @Test
    public void findAll() {
        service.save(users);

        users.forEach(
                x -> assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void delete() {
        User user = users.getFirst();

        service.save(user);
        service.delete(user);

        checker(user);
    }

    @Test
    public void deleteWithOrdersCollection() {
        User user = users.getFirst();

        service.save(user);
        ordersService.save(createOrdersCollection(user));
        service.delete(user);

        checker(user);
    }

    @Test
    public void deleteById() {
        User user = users.getFirst();

        service.save(user);
        service.delete(user.getId());

        checker(user);
    }

    @Test
    public void deleteByIdWithOrdersCollection() {
        User user = users.getFirst();

        service.save(user);
        ordersService.save(createOrdersCollection(user));
        service.delete(user.getId());

        checker(user);
    }

    @Test
    public void deleteCollection() {
        LinkedList<User> users = this.users;

        service.save(users);
        service.delete(users);

        users.forEach(this::checker);
    }

    public LinkedList<User> createUsersCollection() {
        LinkedList<User> users = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User("123" + i * 100, "123", "123" + i * 100);

            users.add(user);
        }
        return users;
    }

    public LinkedList<Order> createOrdersCollection(User user) {
        LinkedList<Order> orders = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Category category = new Category("category" + i);
            categoriesService.save(category);

            Order order = new Order(new BigDecimal("123" + i * 100), false);

            Book book = new Book("123" + i);
            book.setCategory(category);
            booksService.save(book);
            order.getBooks().add(book);

            book = new Book("1234" + i);
            book.setCategory(category);
            booksService.save(book);
            order.getBooks().add(book);

            order.setUser(user);
            orders.add(order);

            categories.add(category);
        }
        return orders;
    }


    private void checker(User user) {
        user.getOrders().forEach(
                x -> {
                    assertNull(service.findOne(x.getId()));
                    x.getBooks().forEach(
                            x1 -> assertNotNull(booksService.findOne(x1))
                    );
                }
        );

        assertNull(service.findOne(user));
    }
}