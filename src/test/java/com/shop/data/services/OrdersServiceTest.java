package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
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

public class OrdersServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private OrdersService service;
    @Autowired
    private BooksService booksService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UsersService usersService;

    private LinkedList<Order> orders;

    @Before
    public void setUp() {
        orders = createOrdersCollection();
    }

    @After
    public void afterEachTest() {
        for (int i = 0; i < 3; i++)
            categoriesService.delete("category" + i);
        orders.forEach(x -> usersService.delete(x.getUser()));
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
        LinkedList<Order> orders = this.orders;

        service.save(orders);

        orders.forEach(
                x ->
                        assertTrue(x.equals(service.findOne(x.getId())))
        );
    }

    @Test
    public void saveNull() {
        try {
            service.save((Order) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void findOne() {
        service.save(orders.getFirst());

        Order order = service.findOne(orders.getFirst());

        assertNotNull(order);
    }

    @Test
    public void findOneById() {
        service.save(orders.getFirst());

        Order order = service.findOne(orders.getFirst().getId());

        assertNotNull(order);
    }

    @Test
    public void findAll() {
        Iterable<Order> orders = service.findAll();

        orders.forEach(
                x -> {
                    service.save(x);
                    assertNotNull(service.findOne(x.getId()));
                }
        );
    }

    @Test
    public void delete() {
        Order order = orders.getFirst();

        service.save(order);
        service.delete(order);

        checker(order);
    }

    @Test
    public void deleteById() {
        Order order = orders.getFirst();

        service.save(order);
        service.delete(order.getId());

        checker(order);
    }


    @Test
    public void deleteCollection() {
        LinkedList<Order> orders = this.orders;

        service.save(orders);
        service.delete(orders);

        orders.forEach(
                this::checker
        );
    }


    public LinkedList<Order> createOrdersCollection() {
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

            User user = new User("123" + i * 100, "123", "123" + i * 100);
            usersService.save(user);

            order.setUser(user);
            orders.add(order);
        }
        return orders;
    }


    private void checker(Order order) {
        assertNull(service.findOne(order));
        assertNull(addressService.findOne(order.getBillingAddress()));
        assertNull(addressService.findOne(order.getShippingAddress()));

        for (Book book : order.getBooks())
            assertNotNull(booksService.findOne(book));
    }
}
