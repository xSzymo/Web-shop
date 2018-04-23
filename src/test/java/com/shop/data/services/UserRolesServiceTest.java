package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class UserRolesServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private UserRolesService service;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private BooksService booksService;
    @Autowired
    private UsersService usersService;

    private LinkedList<UserRole> userRoles;

    @Before
    public void beforeEachTest() {
        userRoles = createUserRolesWithUsersCollection();
    }

    @After
    public void afterEachTest() {
        service.delete(userRoles);
    }

    @Test
    public void saveOne() {
        UserRole address = createUserRolesWithUsersCollection().getFirst();

        service.save(address);

        assertNotNull(service.findOne(address.getId()));
    }

    @Test
    public void saveCollection() {
        LinkedList<UserRole> addresses = this.userRoles;

        service.save(addresses);

        addresses.forEach(x -> assertNotNull(service.findOne(x.getId())));
    }

    @Test
    public void saveNull() {
        try {
            service.save((UserRole) null);
        } catch (Exception e) {
            fail("Exception should not be throw " + e.getMessage());
        }
    }


    @Test
    public void findOne() {
        service.save(userRoles.getFirst());

        UserRole address = service.findOne(this.userRoles.getFirst());

        assertNotNull(address);
    }

    @Test
    public void findOneById() {
        service.save(userRoles.getFirst());

        UserRole address = service.findOne(this.userRoles.getFirst().getId());

        assertNotNull(address);
    }

    @Test
    public void findAll() {
        service.save(userRoles);

        Iterable<UserRole> addresses = service.findAll();

        addresses.forEach(
                x ->
                        assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void findNull() {
        try {
            service.findOne((UserRole) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void delete() {
        UserRole address = userRoles.getFirst();

        service.save(address);
        service.delete(address);

        assertNull(service.findOne(address.getId()));
    }

    @Test
    public void deleteById() {
        UserRole address = userRoles.getFirst();

        service.save(address);
        service.delete(address.getId());

        assertNull(service.findOne(address.getId()));
    }

    @Test
    public void deleteCollection() {
        LinkedList<UserRole> addresses = this.userRoles;

        service.save(addresses);
        service.delete(addresses);

        addresses.forEach(
                x -> assertNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void deleteNull() {
        try {
            service.delete((UserRole) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    private LinkedList<UserRole> createUserRolesCollection() {
        LinkedList<UserRole> userRoles = new LinkedList<>();

        userRoles.add(new UserRole("Admin"));
        userRoles.add(new UserRole("User"));

        return userRoles;
    }

    private LinkedList<UserRole> createUserRolesWithUsersCollection() {
        LinkedList<UserRole> userRoles = new LinkedList<>();

        UserRole admin = new UserRole("Admin");
        admin.setUsers(createOUsersCollection());

        UserRole user = new UserRole("User");
        user.setUsers(createOUsersCollection());

        userRoles.add(admin);
        userRoles.add(user);
        return userRoles;
    }

    private LinkedList<User> createOUsersCollection() {
        LinkedList<User> users = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User("abc" + i, "abc" + i, "abc" + i);

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
            user.getOrders().add(order);

            usersService.save(user);
        }
        return users;
    }
}
