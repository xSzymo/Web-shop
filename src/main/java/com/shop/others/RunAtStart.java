package com.shop.others;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Users;

@Component
public class RunAtStart {
	
	public RunAtStart() {
		
	}
	
	@PostConstruct
	public void runAtStart() {

		Collection<Books> b = new LinkedHashSet<Books>();
		b.add(new Books("boom"));
		b.add(new Books("halo"));
		RepositoriesAccess.booksRepository.save(new Books("noname"));
		RepositoriesAccess.categoriesRepository.save(new Categories("horror", b));

		Users u = new Users("admin", "admin", "admin@wp.pl");
		u.setIsAdmin(true);
		RepositoriesAccess.usersRepository.save(u);
		RepositoriesAccess.usersRepository.save(new Users("person", "person", "person@wp.pl"));
		RepositoriesAccess.usersRepository.save(new Users("personerino", "personerino", "personerino@wp.pl"));
	}
}
