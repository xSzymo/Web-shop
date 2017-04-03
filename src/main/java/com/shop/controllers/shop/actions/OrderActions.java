package com.shop.controllers.shop.actions;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.shop.controllers.shop.Basket;
import com.shop.controllers.shop.Shop;
import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

public class OrderActions {
	public static String saveOrderAndReturnMessage(String shippingAddressStreet, String shippingAddressPostalCode,
			String shippingAddressCity, String shippingAddressCountry, String billingAddressStreet,
			String billingAddressPostalCode, String billingAddressCity, String billingAddressCountry, Object payment,
			String couponCode, String eMail, HttpServletRequest request) {
		LinkedList<Books> secondBasket = Shop.getBasketWithAllBooks(request);
		HashSet<Books> basket = Shop.getBasket(request);
		BigDecimal price = Basket.toCalculate(secondBasket);

		EnumPayments[] kindOfPayment = EnumPayments.values();
		EnumPayments paymentType = null;

		for (EnumPayments x : kindOfPayment)
			if (x.name().equals(payment))
				paymentType = x;
		Address shippingAddress = new Address(shippingAddressStreet, shippingAddressPostalCode, shippingAddressCity,
				shippingAddressCountry);
		Address billingAddress = new Address(billingAddressStreet, billingAddressPostalCode, billingAddressCity,
				billingAddressCountry);
		RepositoriesAccess.addressRepository.save(shippingAddress);
		RepositoriesAccess.addressRepository.save(billingAddress);

		CouponCodes coupon = RepositoriesAccess.couponCodesRepository.findByCode(couponCode);
		if (coupon != null)
			RepositoriesAccess.couponCodesRepository.save(coupon);
		else
			coupon = null;

		Orders order = new Orders();
		order.setBillingAddress(shippingAddress);
		order.setShippingAddress(billingAddress);
		order.setPaymentMethod(paymentType);
		order.setCouponCodes(coupon);
		order.setPrice(price);
		RepositoriesAccess.ordersRepository.save(order);

		String text = null;

		if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
			Users user = RepositoriesAccess.usersRepository
					.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
			user.getOrders().add(order);
			RepositoriesAccess.usersRepository.save(user);

			text = EmailText.textFromUser(price, paymentType, shippingAddressStreet, shippingAddressPostalCode,
					shippingAddressCity, shippingAddressCountry, billingAddressStreet, billingAddressPostalCode,
					billingAddressCity, billingAddressCountry, user.getLogin(), basket);
		} else {
			text = EmailText.textFromAnonymous(price, paymentType, shippingAddressStreet, shippingAddressPostalCode,
					shippingAddressCity, shippingAddressCountry, billingAddressStreet, billingAddressPostalCode,
					billingAddressCity, billingAddressCountry, basket);
		}
		return text;
	}
}
