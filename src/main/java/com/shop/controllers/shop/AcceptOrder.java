package com.shop.controllers.shop;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;
import com.shop.others.SendEmail;

@Controller
@RequestMapping("shop")
public class AcceptOrder {

	// add for email
	@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("accept")
	public String acceptForUser(HttpServletRequest request, Model model,
			@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,

			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry,
			@RequestParam("couponCode") String couponCode, @RequestParam("email") String email,
			@RequestParam("payment") Object payment) {
		LinkedList<Books> secondBasket = Shop.getBasketWithAllBooks(request);
		HashSet<Books> basket = Shop.getBasket(request);
		BigDecimal price = Basket.toCalculate(secondBasket);

		EnumPayments[] kindOfPayment = EnumPayments.values();
		EnumPayments paymentType = null;

		for (EnumPayments x : kindOfPayment)
			if (x.name().equals(payment))
				paymentType = x;

		Users user = RepositoriesAccess.usersRepository
				.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		Address shippingAddress = new Address(shippingAddressStreet, shippingAddressPostalCode, shippingAddressCity,
				shippingAddressCountry);
		RepositoriesAccess.addressRepository.save(shippingAddress);
		Address billingAddress = new Address(billingAddressStreet, billingAddressPostalCode, billingAddressCity,
				billingAddressCountry);
		RepositoriesAccess.addressRepository.save(billingAddress);

		Orders order = new Orders();
		order.setBillingAddress(shippingAddress);
		order.setShippingAddress(billingAddress);

		CouponCodes coupon = RepositoriesAccess.couponCodesRepository.findByCode(couponCode);
		if (coupon != null) {
			order.setCouponCodes(coupon);
			RepositoriesAccess.couponCodesRepository.save(coupon);
		}
		order.setPrice(price);
		order.setPaymentMethod(paymentType);
		RepositoriesAccess.ordersRepository.save(order);
		user.getOrders().add(order);
		RepositoriesAccess.usersRepository.save(user);

		String text = "";
		text += "Hello user " + user.getLogin() + "\n\n";
		text += "Price to pay : " + price + "\n";
		text += "\nBooks in order :\n";
		for (Books x : basket)
			text += "\t " + x.getName() + "\n";

		text += "\nPayment type : ";
		text += "\n\t" + paymentType;

		text += "\n\nShippingAddress : ";
		text += "\n\tStreet : " + shippingAddressStreet;
		text += "\n\tPostalCode : " + shippingAddressPostalCode;
		text += "\n\tCity : " + shippingAddressCity;
		text += "\n\tCountry : " + shippingAddressCountry;

		text += "\nBillingAddress : ";
		text += "\n\tStreet : " + billingAddressStreet;
		text += "\n\tPostalCode : " + billingAddressPostalCode;
		text += "\n\tCity : " + billingAddressCity;
		text += "\n\tCountry : " + billingAddressCountry;
		SendEmail.sendEmailWithOrderFromUser(text, request);

		model.addAttribute("success", "success");
		return "shop/options/userOrder";
	}

	@RequestMapping("acceptAnonymous")
	public String acceptForAnonymous(HttpServletRequest request, Model model,
			@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,

			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry, @RequestParam("email") String email,
			@RequestParam("couponCode") String couponCode, @RequestParam("payment") Object payment) {
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
		RepositoriesAccess.addressRepository.save(shippingAddress);
		Address billingAddress = new Address(billingAddressStreet, billingAddressPostalCode, billingAddressCity,
				billingAddressCountry);
		RepositoriesAccess.addressRepository.save(billingAddress);

		Orders order = new Orders();
		order.setBillingAddress(shippingAddress);
		order.setShippingAddress(billingAddress);

		CouponCodes coupon = RepositoriesAccess.couponCodesRepository.findByCode(couponCode);
		if (coupon != null) {
			order.setCouponCodes(coupon);
			RepositoriesAccess.couponCodesRepository.save(coupon);
		}
		order.setPrice(price);
		order.setPaymentMethod(paymentType);
		RepositoriesAccess.ordersRepository.save(order);

		String text = "";
		text += "Price to pay : " + price + "\n";
		text += "\nBooks in order :\n";
		for (Books x : basket)
			text += "\t " + x.getName() + "\n";

		text += "\nPayment type : ";
		text += "\n\t" + paymentType;

		text += "\n\nShippingAddress : ";
		text += "\n\tStreet : " + shippingAddressStreet;
		text += "\n\tPostalCode : " + shippingAddressPostalCode;
		text += "\n\tCity : " + shippingAddressCity;
		text += "\n\tCountry : " + shippingAddressCountry;

		text += "\nBillingAddress : ";
		text += "\n\tStreet : " + billingAddressStreet;
		text += "\n\tPostalCode : " + billingAddressPostalCode;
		text += "\n\tCity : " + billingAddressCity;
		text += "\n\tCountry : " + billingAddressCountry;

		SendEmail.sendEmailWithOrderFromAnonymous(text);

		model.addAttribute("success", "success");
		return "shop/options/userOrder";
	}
}
