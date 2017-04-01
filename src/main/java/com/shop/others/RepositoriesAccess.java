package com.shop.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.data.repositories.AdressRepository;
import com.shop.data.repositories.BooksRepository;
import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.repositories.CouponCodesRepository;
import com.shop.data.repositories.OrdersRepository;
import com.shop.data.repositories.PicturesRepository;
import com.shop.data.repositories.UserRolesRepository;
import com.shop.data.repositories.UsersRepository;

@Component
public class RepositoriesAccess {
	@Autowired
	public static UsersRepository usersRepository;
	@Autowired
	public static CategoriesRepository categoriesRepository;
	@Autowired
	public static BooksRepository booksRepository;
	@Autowired
	public static AdressRepository addressRepository;
	@Autowired
	public static CouponCodesRepository couponCodesRepository;
	@Autowired
	public static PicturesRepository picturesRepository;
	@Autowired
	public static OrdersRepository ordersRepository;
	@Autowired
	public static UserRolesRepository userRolesRepository;

	private RepositoriesAccess(UsersRepository usersRepository, CategoriesRepository categoriesRepository,
			BooksRepository booksRepository, AdressRepository adressRepository,
			CouponCodesRepository couponCodesRepository, PicturesRepository picturesRepository,
			OrdersRepository ordersRepository, UserRolesRepository userRolesRepository) {
		
		RepositoriesAccess.usersRepository = usersRepository;
		RepositoriesAccess.categoriesRepository = categoriesRepository;
		RepositoriesAccess.booksRepository = booksRepository;
		RepositoriesAccess.addressRepository = adressRepository;
		RepositoriesAccess.couponCodesRepository = couponCodesRepository;
		RepositoriesAccess.picturesRepository = picturesRepository;
		RepositoriesAccess.ordersRepository = ordersRepository;
		RepositoriesAccess.userRolesRepository = userRolesRepository;
	}
	

}
