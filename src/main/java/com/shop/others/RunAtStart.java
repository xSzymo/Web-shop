package com.shop.others;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;

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

		Collection<Books> booksCollection = new LinkedHashSet<Books>();
		booksCollection.add(new Books("boom"));
		booksCollection.add(new Books("halo"));
		RepositoriesAccess.booksRepository.save(new Books("noname"));
		
		Collection<Books> booksCollection1 = new LinkedHashSet<Books>();
		booksCollection1.add(new Books("xzvckmhvzixkcjvblkshjd"));
		RepositoriesAccess.categoriesRepository.save(new Categories("halohalo", booksCollection1));
		
		RepositoriesAccess.categoriesRepository.save(new Categories("horror", booksCollection));

		Users user = new Users("admin", "admin", "admin@wp.pl");
		user.setAddress(a);
		Users user1 = new Users("user", "user", "user@wp.pl");
		RepositoriesAccess.usersRepository.save(user);
		RepositoriesAccess.usersRepository.save(user1);
		RepositoriesAccess.usersRepository.save(new Users("person", "person", "person@wp.pl"));
		RepositoriesAccess.usersRepository.save(new Users("personerino", "personerino", "personerino@wp.pl"));
				
		Categories categories = new Categories();
		categories.setName("mmo");
		RepositoriesAccess.categoriesRepository.save(categories);
		
		
		CouponCodes couponCode = new CouponCodes(0.25, "123");
		RepositoriesAccess.couponCodesRepository.save(couponCode);
		
		Address shippingAddressId = new Address("czerwona", "33-45", "bielsko", "polska");
		Address billingAddressId = new Address("czerwona123", "33-45", "bielsko", "poland");
		RepositoriesAccess.addressRepository.save(shippingAddressId);
		RepositoriesAccess.addressRepository.save(billingAddressId);

		Orders o = new Orders(new BigDecimal("100"), EnumPayments.BANKTRANSFER, shippingAddressId, billingAddressId, couponCode);
		o.setBooks(booksCollection);
		RepositoriesAccess.ordersRepository.save(o);
		
		Collection<Books> books123 = new ArrayList<Books>();
		books123.add(new Books("halohalo123123"));
		books123.add(new Books("vczx"));
		books123.add(new Books("halohaldrehr123o123123"));
		RepositoriesAccess.ordersRepository.save(new Orders((long) 10, new BigDecimal("100000"), false, books123));

		Orders o1 = new Orders(new BigDecimal("50"), true);
		o.setBooks(booksCollection);
		RepositoriesAccess.ordersRepository.save(o1);

		user.getOrders().add(o);
		RepositoriesAccess.usersRepository.save(user);

		user1.getOrders().add(o1);
		RepositoriesAccess.usersRepository.save(user1);

		Collection<Users> users = new LinkedHashSet<Users>();
		users.add(user);
		
		UserRole r = new UserRole();
		r.setRole("ROLE_ADMIN");
		r.setUser(users);
		RepositoriesAccess.userRolesRepository.save(r);
		

		Collection<Users> users1 = new LinkedHashSet<Users>();
		users1.add(user1);
		
		UserRole r1 = new UserRole();
		r1.setRole("ROLE_USER");
		r1.setUser(users1);
		RepositoriesAccess.userRolesRepository.save(r1);
	}
}
