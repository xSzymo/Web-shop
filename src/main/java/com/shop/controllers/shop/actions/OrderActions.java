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
import com.shop.data.tables.Book;
import com.shop.data.tables.CouponCode;
import com.shop.data.tables.Order;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;

public class OrderActions {
	public static String saveOrderAndReturnMessage(String shippingAddressStreet, String shippingAddressPostalCode,
			String shippingAddressCity, String shippingAddressCountry, String billingAddressStreet,
			String billingAddressPostalCode, String billingAddressCity, String billingAddressCountry, Object payment,
			String couponCode, String eMail, HttpServletRequest request) {
		LinkedList<Book> secondBasket = Shop.getBasketWithAllBooks(request);
		HashSet<Book> basket = Shop.getBasket(request);
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

		CouponCode coupon = RepositoriesAccess.couponCodesRepository.findByCode(couponCode);
		if (coupon != null)
			RepositoriesAccess.couponCodesRepository.save(coupon);
		else
			coupon = null;

		Order order = new Order();
		order.setBillingAddress(shippingAddress);
		order.setShippingAddress(billingAddress);
		order.setPaymentMethod(paymentType);
		order.setCouponCodes(coupon);
		order.setPrice(price);
		RepositoriesAccess.ordersRepository.save(order);

		String text = null;

		if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
			User user = RepositoriesAccess.usersRepository
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
