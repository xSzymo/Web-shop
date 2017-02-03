package com.shop.data.operations;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;


public class CategoriesOperations {
//	static Categories c;
//	
//	public static void add(Categories category, Books book) {
////		books.add(book);
////		System.out.println(category.getName());
////		category.getBooks();
////		System.out.println(category.getName());
////		category.setBooks(books);
//		
//	    //Hibernate.initialize(c.getBooks());
//		c = new Categories();
//		c = category;
//		//Hibernate.initialize(c.getBooks());
//		
//		
//		if(c.getBooks().size() > 0)
//			System.out.println("hey");
//		else
//			System.out.println("hey1");
//		c.getBooks().add(new Books("halo"));
//		
//		RepositoriesAccess.categoriesRepository.save(c);
//		
//		//category.getBooks().size()
//	}
//	
//	@Transactional 
//	public static void halo() { 
//	    Hibernate.initialize(c.getBooks());
//	}
}
