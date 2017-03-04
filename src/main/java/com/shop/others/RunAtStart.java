package com.shop.others;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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

		Collection<Books> booksCollection = new LinkedHashSet<Books>();
		booksCollection.add(new Books("boom"));
		booksCollection.add(new Books("halo"));
		RepositoriesAccess.booksRepository.save(new Books("noname"));
		RepositoriesAccess.categoriesRepository.save(new Categories("horror", booksCollection));

		Users user = new Users("admin", "admin", "admin@wp.pl");
		Users user1 = new Users("user", "user", "user@wp.pl");
		RepositoriesAccess.usersRepository.save(user);
		RepositoriesAccess.usersRepository.save(user1);
		RepositoriesAccess.usersRepository.save(new Users("person", "person", "person@wp.pl"));
		RepositoriesAccess.usersRepository.save(new Users("personerino", "personerino", "personerino@wp.pl"));
				
		Categories categories = new Categories();
		categories.setName("mmo");
		RepositoriesAccess.categoriesRepository.save(categories);
		
		
		CouponCodes couponCode = new CouponCodes(new BigDecimal("500"), "123");
		RepositoriesAccess.couponCodesRepository.save(couponCode);
		
		Address shippingAddressId = new Address("cisowa", "33-45", "bielsko", "polska");
		Address billingAddressId = new Address("cisowa123", "33-45", "bielsko", "poland");
		RepositoriesAccess.adressRepository.save(shippingAddressId);
		RepositoriesAccess.adressRepository.save(billingAddressId);
		
		Orders o = new Orders(new BigDecimal("100"), EnumPayments.BANKTRANSFER, shippingAddressId, billingAddressId, couponCode);
		o.setBooks(booksCollection);
		RepositoriesAccess.ordersRepository.save(o);
		
		user.getOrders().add(o);
		RepositoriesAccess.usersRepository.save(user);

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
