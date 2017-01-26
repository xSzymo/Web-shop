package com.shop.others;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.AfterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.data.repositories.AdressRepository;
import com.shop.data.repositories.BooksRepository;
import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.repositories.CouponCodesRepository;
import com.shop.data.repositories.OrdersRepository;
import com.shop.data.repositories.PicturesRepository;
import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Users;

@Component
public class RepositoriesAccess {
	@Autowired
	public static UsersRepository usersRepository;
	@Autowired
	public static CategoriesRepository categoriesRepository;
	@Autowired
	public static BooksRepository booksRepository;
	@Autowired
	public static AdressRepository adressRepository;
	@Autowired
	public static CouponCodesRepository couponCodesRepository;
	@Autowired
	public static PicturesRepository picturesRepository;
	@Autowired
	public static OrdersRepository ordersRepository;

	private RepositoriesAccess(UsersRepository usersRepository, CategoriesRepository categoriesRepository,
			BooksRepository booksRepository, AdressRepository adressRepository,
			CouponCodesRepository couponCodesRepository, PicturesRepository picturesRepository,
			OrdersRepository ordersRepository) {
		
		RepositoriesAccess.usersRepository = usersRepository;
		RepositoriesAccess.categoriesRepository = categoriesRepository;
		RepositoriesAccess.booksRepository = booksRepository;
		RepositoriesAccess.adressRepository = adressRepository;
		RepositoriesAccess.couponCodesRepository = couponCodesRepository;
		RepositoriesAccess.picturesRepository = picturesRepository;
		RepositoriesAccess.ordersRepository = ordersRepository;
	}
	

}
