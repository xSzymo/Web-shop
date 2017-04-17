package com.shop.others;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Address;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.CouponCode;
import com.shop.data.tables.Order;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.User;

@Component
public class RunAtStart {
	
	public RunAtStart() {
		
	}
	
	@PostConstruct
	public void runAtStart() {
		
		Address a = new Address();
		a.setCity("CITI");
		a.setCountry("CONTRY");
		a.setStreet("STRIT");
		a.setPostalCode("45-4555555");
		RepositoriesAccess.addressRepository.save(a);

		Collection<Book> booksCollection = new LinkedHashSet<Book>();
		booksCollection.add(new Book("boom"));
		booksCollection.add(new Book("halo"));
		RepositoriesAccess.booksRepository.save(new Book("noname"));
		
		Collection<Book> booksCollection1 = new LinkedHashSet<Book>();
		booksCollection1.add(new Book("xzvckmhvzixkcjvblkshjd"));
		RepositoriesAccess.categoriesRepository.save(new Category("halohalo", booksCollection1));
		
		RepositoriesAccess.categoriesRepository.save(new Category("horror", booksCollection));

		User user = new User("admin", "admin", "admin@wp.pl");
		user.setAddress(a);
		User user1 = new User("user", "user", "user@wp.pl");
		RepositoriesAccess.usersRepository.save(user);
		RepositoriesAccess.usersRepository.save(user1);
		RepositoriesAccess.usersRepository.save(new User("person", "person", "person@wp.pl"));
		RepositoriesAccess.usersRepository.save(new User("personerino", "personerino", "personerino@wp.pl"));
				
		Category categories = new Category();
		categories.setName("mmo");
		RepositoriesAccess.categoriesRepository.save(categories);
		
		
		CouponCode couponCode = new CouponCode(0.25, "123");
		RepositoriesAccess.couponCodesRepository.save(couponCode);
		
		Address shippingAddressId = new Address("czerwona", "33-45", "bielsko", "polska");
		Address billingAddressId = new Address("czerwona123", "33-45", "bielsko", "poland");
		RepositoriesAccess.addressRepository.save(shippingAddressId);
		RepositoriesAccess.addressRepository.save(billingAddressId);

		Order o = new Order(new BigDecimal("100"), EnumPayments.BANKTRANSFER, shippingAddressId, billingAddressId, couponCode);
		o.setBooks(booksCollection);
		RepositoriesAccess.ordersRepository.save(o);
		
		Collection<Book> books123 = new ArrayList<Book>();
		books123.add(new Book("halohalo123123"));
		books123.add(new Book("vczx"));
		books123.add(new Book("halohaldrehr123o123123"));
		RepositoriesAccess.ordersRepository.save(new Order((long) 10, new BigDecimal("100000"), false, books123));

		Order o1 = new Order(new BigDecimal("50"), true);
		o.setBooks(booksCollection);
		RepositoriesAccess.ordersRepository.save(o1);

		user.getOrders().add(o);
		RepositoriesAccess.usersRepository.save(user);

		user1.getOrders().add(o1);
		RepositoriesAccess.usersRepository.save(user1);

		Collection<User> users = new LinkedHashSet<User>();
		users.add(user);
		
		UserRole r = new UserRole();
		r.setRole("ROLE_ADMIN");
		r.setUser(users);
		RepositoriesAccess.userRolesRepository.save(r);
		

		Collection<User> users1 = new LinkedHashSet<User>();
		users1.add(user1);
		
		UserRole r1 = new UserRole();
		r1.setRole("ROLE_USER");
		r1.setUser(users1);
		RepositoriesAccess.userRolesRepository.save(r1);
	}
}
