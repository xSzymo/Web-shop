package com.shop.others;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct;

import org.junit.After;
import org.junit.AfterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.data.repositories.AdressRepository;
import com.shop.data.repositories.BooksRepository;
import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Users;

@Component
public class RunAtStart {
	@Autowired
	public static UsersRepository usersRepository;
	@Autowired
	public static CategoriesRepository categoriesRepository;
	@Autowired
	public static BooksRepository booksRepository;
	@Autowired
	public static AdressRepository adressRepository;

	@Autowired
	public RunAtStart(UsersRepository usersRepository, CategoriesRepository categoriesRepository,
			BooksRepository booksRepository, AdressRepository adressRepository) {
		this.usersRepository = usersRepository;
		this.categoriesRepository = categoriesRepository;
		this.booksRepository = booksRepository;
		this.adressRepository = adressRepository;
	}

	@PostConstruct
	public void runAtStart() {

		Collection<Books> b = new LinkedHashSet<Books>();
		b.add(new Books("boom"));
		b.add(new Books("halo"));
		booksRepository.save(new Books("noname"));
		categoriesRepository.save(new Categories("horror", b));

		Users u = new Users("admin", "admin", "admin@wp.pl");
		u.setIsAdmin(true);
		usersRepository.save(u);
		usersRepository.save(new Users("person", "person", "person@wp.pl"));
		usersRepository.save(new Users("personerino", "personerino", "personerino@wp.pl"));
	}
}
