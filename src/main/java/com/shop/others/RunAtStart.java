package com.shop.others;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.enums.EnumPayments;
import com.shop.data.services.*;
import com.shop.data.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public class RunAtStart {
    private AddressService addressService;
    private BooksService booksService;
    private CategoriesService categoriesService;
    private UsersService usersService;
    private OrdersService ordersService;
    private UserRolesService userRolesService;
    private CouponCodesService couponCodesService;

    @Autowired
    public RunAtStart(AddressService addressService, BooksService booksService, CategoriesService categoriesService, UsersService usersService, OrdersService ordersService, UserRolesService userRolesService, CouponCodesService couponCodesService) {
        this.addressService = addressService;
        this.booksService = booksService;
        this.categoriesService = categoriesService;
        this.usersService = usersService;
        this.ordersService = ordersService;
        this.userRolesService = userRolesService;
        this.couponCodesService = couponCodesService;
    }

    @PostConstruct
    public void runAtStart() throws InterruptedException {
        Thread.sleep(1000 * 10);

        if (ApplicationProperties.FALSE_WHILE_RUNNING_DB_TESTS) {
            Address a = new Address();
            a.setCity("CITI");
            a.setCountry("CONTRY");
            a.setStreet("STRIT");
            a.setPostalCode("45-4555555");
            addressService.save(a);

            Collection<Book> booksCollection = new LinkedHashSet<Book>();
            booksCollection.add(new Book("boom"));
            booksCollection.add(new Book("halo"));
            booksService.save(new Book("noname"));

            Collection<Book> booksCollection1 = new LinkedHashSet<Book>();
            booksCollection1.add(new Book("xzvckmhvzixkcjvblkshjd"));
            categoriesService.save(new Category("halohalo", booksCollection1));

            Book b = new Book("rere");
            booksCollection.add(b);
            Category simpleCategory = new Category("horror", booksCollection);
            // b.category = simpleCategory;
            categoriesService.save(simpleCategory);
            booksService.save(b);


            User user = new User("admin", "admin", "admin@wp.pl");
            user.setAddress(a);
            User user1 = new User("user", "user", "user@wp.pl");
            usersService.save(user);
            usersService.save(user1);
            usersService.save(new User("person", "person", "person@wp.pl"));
            usersService.save(new User("personerino", "personerino", "personerino@wp.pl"));

            Category categories = new Category("bb");
            categories.setName("mmo");
            categoriesService.save(categories);


            CouponCode couponCode = new CouponCode(0.25, "123");
            couponCodesService.save(couponCode);

            Address shippingAddressId = new Address("czerwona", "33-45", "bielsko", "polska");
            Address billingAddressId = new Address("czerwona123", "33-45", "bielsko", "poland");
            addressService.save(shippingAddressId);
            addressService.save(billingAddressId);

            Order o = new Order(new BigDecimal("100"), EnumPayments.BANKTRANSFER, shippingAddressId, billingAddressId, couponCode);
            o.setBooks(booksCollection);
            ordersService.save(o);

            Collection<Book> books123 = new ArrayList<Book>();
            books123.add((new Book("halohalo123123")));
            books123.add((new Book("vczx")));
            books123.add((new Book("halohaldrehr123o123123")));
            //RepositoriesAccess.booksRepository.save(books123);
            ordersService.save(new Order(new BigDecimal("100000"), false, books123));

            Order o1 = new Order(new BigDecimal("50"), true);
            o.setBooks(booksCollection);
            ordersService.save(o1);

            user.getOrders().add(o);
            usersService.save(user);

            user1.getOrders().add(o1);
            usersService.save(user1);

            Collection<User> users = new LinkedHashSet<User>();
            users.add(user);

            UserRole r = new UserRole("ROLE_ADMIN");
            r.setUsers(users);
            userRolesService.save(r);


            Collection<User> users1 = new LinkedHashSet<User>();
            users1.add(user1);

            UserRole r1 = new UserRole("ROLE_USER");
            r1.setUsers(users1);
            userRolesService.save(r1);
        }
    }
}
