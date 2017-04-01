package com.shop.controllers.administratorSite.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/orders")
public class UpdateOrders {

	@RequestMapping("/update")
	public String updateSite(Model model) {
		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
		model.addAttribute("orders", orders);
		return "administratorSite/ordersManager/update";
	}

	@RequestMapping(value = "updateOrder/{orderId}")
	public String updateOne(@PathVariable Long orderId, Model model) {
		Orders foundOrder = RepositoriesAccess.ordersRepository.findById(orderId);

		if (foundOrder == null)
			model.addAttribute("msg", "not found");

		EnumPayments[] kindOfPayment = EnumPayments.values();
		String[] paymentName = new String[kindOfPayment.length];
		int i = 0;
		for (EnumPayments x : kindOfPayment)
			paymentName[i++] = x.name();

		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);

		if (foundOrder.getPaymentMethod() != null)
			model.addAttribute("orderPayment", foundOrder.getPaymentMethod().toString());

		model.addAttribute("payments", paymentName);
		model.addAttribute("order", foundOrder);
		return "/administratorSite/ordersManager/updateOneOrder";
	}

	@RequestMapping("updateOrder/update")
	public String updateOrder(@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,

			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry,

			@RequestParam("couponCode") String couponCode,
			@RequestParam("couponCodeDiscount") double couponCodeDiscount,

			@RequestParam("orderId") Long orderId,

			@RequestParam("payment") Object payment, @RequestParam("price") String price,
			@RequestParam("realized") boolean realized, HttpServletRequest request, Model model, String... books) {

		EnumPayments[] kindOfPayment = EnumPayments.values();
		EnumPayments paymentType = null;

		for (EnumPayments x : kindOfPayment)
			if (x.name().equals(payment))
				paymentType = x;

		System.out.println(paymentType);

		// System.out.println(payment);

		Orders order = RepositoriesAccess.ordersRepository.findById(orderId);
		Address billingAddress = order.getBillingAddress();
		Address shippingAddress = order.getShippingAddress();
		CouponCodes couponCodes = order.getCouponCodes();

		if (billingAddress != null) {
			billingAddress.setCity(billingAddressCity);
			billingAddress.setCountry(billingAddressCountry);
			billingAddress.setPostalCode(billingAddressPostalCode);
			billingAddress.setStreet(billingAddressStreet);
		} else {
			billingAddress = new Address();
			billingAddress.setCity(billingAddressCity);
			billingAddress.setCountry(billingAddressCountry);
			billingAddress.setPostalCode(billingAddressPostalCode);
			billingAddress.setStreet(billingAddressStreet);
		}

		if (shippingAddress != null) {
			shippingAddress.setCity(shippingAddressCity);
			shippingAddress.setCountry(shippingAddressCountry);
			shippingAddress.setPostalCode(shippingAddressPostalCode);
			shippingAddress.setStreet(shippingAddressStreet);
		} else {
			shippingAddress = new Address();
			shippingAddress.setCity(shippingAddressCity);
			shippingAddress.setCountry(shippingAddressCountry);
			shippingAddress.setPostalCode(shippingAddressPostalCode);
			shippingAddress.setStreet(shippingAddressStreet);
		}

		if (couponCodes != null) {
			couponCodes.setCode(couponCode);
			couponCodes.setCodeDiscount(couponCodeDiscount);
		} else {
			couponCodes = new CouponCodes();
			couponCodes.setCode(couponCode);
			couponCodes.setCodeDiscount(couponCodeDiscount);
			order.setCouponCodes(couponCodes);
		}
		RepositoriesAccess.addressRepository.save(billingAddress);
		RepositoriesAccess.addressRepository.save(shippingAddress);
		RepositoriesAccess.couponCodesRepository.save(couponCodes);

		order.setPrice(new BigDecimal(price));
		order.setRealized(realized);
		order.setPaymentMethod(paymentType);
		order.setBillingAddress(billingAddress);
		order.setShippingAddress(shippingAddress);

		ArrayList<Books> b = new ArrayList<Books>();

		for (int i = 0; i < books.length; i++) {
			if (RepositoriesAccess.booksRepository.findByName(books[i]) != null) {
				b.add(RepositoriesAccess.booksRepository.findByName(books[i]));
			}
		}

		RepositoriesAccess.booksRepository.save(b);

		order.getBooks().addAll(b);
		RepositoriesAccess.ordersRepository.save(order);
		// System.out.println(order.getBooks());

		// if(order.getPaymentMethod() != null)
		// model.addAttribute("orderPayment",
		// order.getPaymentMethod().toString());
		return updateOne(order.getId(), model);
	}

	@RequestMapping("updateOrder/createBooks123")
	public String createBook(@RequestParam("orderId") Long orderId, Model model, HttpServletRequest request) {

		Iterable<Books> allBooks = RepositoriesAccess.booksRepository.findAll();
		LinkedList<Books> chosenBooks = new LinkedList<Books>();
		chosenBooks.clear();

		for (Books x : allBooks)
			if (request.getParameter(x.getName()) != null)
				chosenBooks.add(x);

		Orders order = RepositoriesAccess.ordersRepository.findById(orderId);
		order.setBooks(chosenBooks);

		RepositoriesAccess.ordersRepository.save(order);

		return updateOne(orderId, model);
	}
}
