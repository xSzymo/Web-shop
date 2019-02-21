package com.shop.others;

import com.shop.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoriesAccess {
    @Autowired
    public static UsersRepository usersRepository;
    @Autowired
    public static CategoriesRepository categoriesRepository;
    @Autowired
    public static BooksRepository booksRepository;
    @Autowired
    public static AddressRepository addressRepository;
    @Autowired
    public static CouponCodesRepository couponCodesRepository;
    @Autowired
    public static PicturesRepository picturesRepository;
    @Autowired
    public static OrdersRepository ordersRepository;
    @Autowired
    public static UserRolesRepository userRolesRepository;
    @Autowired
    public static CookiesRepository cookiesRepository;

    private RepositoriesAccess(UsersRepository usersRepository, CategoriesRepository categoriesRepository,
                               BooksRepository booksRepository, AddressRepository addressRepository,
                               CouponCodesRepository couponCodesRepository, PicturesRepository picturesRepository,
                               OrdersRepository ordersRepository, UserRolesRepository userRolesRepository, CookiesRepository cookiesRepository) {

        RepositoriesAccess.usersRepository = usersRepository;
        RepositoriesAccess.categoriesRepository = categoriesRepository;
        RepositoriesAccess.booksRepository = booksRepository;
        RepositoriesAccess.addressRepository = addressRepository;
        RepositoriesAccess.couponCodesRepository = couponCodesRepository;
        RepositoriesAccess.picturesRepository = picturesRepository;
        RepositoriesAccess.ordersRepository = ordersRepository;
        RepositoriesAccess.userRolesRepository = userRolesRepository;
        RepositoriesAccess.cookiesRepository = cookiesRepository;
    }
}
