package com.shop.controllers.administratorSite.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.data.tables.Pictures;
import com.shop.others.RepositoriesAccess;

/*
 * payment method in update not working
 * update for street etc doeasnt work
 */
@Controller
@RequestMapping("administratorSite/orders")
public class OrdersCRUD {

	@RequestMapping
	public String start() {
		return "administratorSite/ordersStartPage";
	}

	@RequestMapping("create")
	public String createSite(Model model) {
		EnumPayments[] kindOfPayment = EnumPayments.values();
		String[] paymentName = new String[kindOfPayment.length];
		int i = 0;
		for (EnumPayments x : kindOfPayment)
			paymentName[i++] = x.name();

		model.addAttribute("payments", paymentName);

		model.addAttribute("books", null);

		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("allBooks", books);
		return "administratorSite/ordersManager/create";
	}

	@RequestMapping("createOrder")
	public String hallo(@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,

			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry,

			@RequestParam("couponCode") String couponCode,
			@RequestParam("couponCodeDiscount") double couponCodeDiscount,

			@RequestParam("billingAddress") Long billingAddressId,
			@RequestParam("shippingAddress") Long shippingAddressId, @RequestParam("couponCodeId") Long couponCodeId,

			@RequestParam("payment") Object payment, @RequestParam("price") String price,
			@RequestParam("realized") boolean realized, HttpServletRequest request, Model model, String... books) {
		EnumPayments[] kindOfPayment = EnumPayments.values();
		EnumPayments paymentType = null;

		for (EnumPayments x : kindOfPayment)
			if (x.name().equals(payment))
				paymentType = x;

		// System.out.println(payment);

		Orders order = new Orders();
		Address billingAddress = null;
		Address shippingAddress = null;
		CouponCodes couponCodes = null;

		if (billingAddressId != null)
			billingAddress = RepositoriesAccess.addressRepository.findById(billingAddressId);
		if (shippingAddressId != null)
			shippingAddress = RepositoriesAccess.addressRepository.findById(shippingAddressId);
		if (couponCodeId != null)
			couponCodes = RepositoriesAccess.couponCodesRepository.findById(couponCodeId);

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
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);

		ArrayList<Books> b = new ArrayList<Books>();

		for (int i = 0; i < books.length; i++) {
			if (RepositoriesAccess.booksRepository.findByName(books[i]) != null) {
				b.add(RepositoriesAccess.booksRepository.findByName(books[i]));
			}
		}

		RepositoriesAccess.booksRepository.save(b);

		order.getBooks().addAll(b);
		RepositoriesAccess.ordersRepository.save(order);

		addNeedObjects(model, couponCodeId, billingAddressId, shippingAddressId, books);

		return "administratorSite/ordersManager/create";
	}

	@RequestMapping("/createAddress")
	public String create1(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country,
			@RequestParam("billingAddress") Long billingAddressId,
			@RequestParam("shippingAddress") Long shippingAddressId, @RequestParam("couponCodeId") Long couponCodeId,
			@RequestParam("address") String address1, Model model, HttpServletRequest request, String... books) {

		Address address = new Address(street, postalCode, city, country);
		RepositoriesAccess.addressRepository.save(address);

		addNeedObjects(model, couponCodeId, billingAddressId, shippingAddressId, books);

		if (address1.equals("shipping"))
			model.addAttribute("shippingAddress", address);
		else
			model.addAttribute("billingAddress", address);

		return "administratorSite/ordersManager/create";
	}

	@RequestMapping("/createCouponCode")
	public String create1(@RequestParam("codeDiscount") String codeDiscount, @RequestParam("code") String code,
			@RequestParam("billingAddress") Long billingAddressId,
			@RequestParam("shippingAddress") Long shippingAddressId, @RequestParam("couponCodeId") Long couponCodeId,
			Model model, HttpServletRequest request, @RequestParam(name = "books", required = false) String... books) {

		addNeedObjects(model, couponCodeId, billingAddressId, shippingAddressId, books);
		CouponCodes couponCodeFound = RepositoriesAccess.couponCodesRepository.findByCode(code);

		if (couponCodeFound != null) {
			model.addAttribute("msgError", "couponCode already exist");
			return "administratorSite/ordersManager/create";
		}
		CouponCodes couponCode = new CouponCodes(Double.parseDouble(codeDiscount), code);

		RepositoriesAccess.couponCodesRepository.save(couponCode);
		model.addAttribute("couponCode", couponCode);

		return "administratorSite/ordersManager/create";
	}

	@RequestMapping("/createBook")
	public String create1(@RequestParam("billingAddress") Long billingAddressId,
			@RequestParam("shippingAddress") Long shippingAddressId, @RequestParam("couponCodeId") Long couponCodeId,
			Model model, HttpServletRequest request) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		LinkedList<Books> chosenBooks = new LinkedList<Books>();
		chosenBooks.clear();

		for (Books x : books)
			if (request.getParameter(x.getName()) != null)
				chosenBooks.add(x);

		String[] books1 = new String[chosenBooks.size()];
		int j = 0;
		for (int i = 0; i < books1.length; i++)
			books1[j++] = chosenBooks.get(i).getName();

		addNeedObjects(model, couponCodeId, billingAddressId, shippingAddressId, books1);

		return "administratorSite/ordersManager/create";
	}

	public void addNeedObjects(Model model, Long couponCodeId, Long billingAddressId, Long shippingAddressId,
			String[] bookNames) {
		if ((couponCodeId != null)) {
			CouponCodes couponCode = RepositoriesAccess.couponCodesRepository.findById(couponCodeId);
			model.addAttribute("couponCode", couponCode);
		}
		if ((billingAddressId != null)) {
			Address billingAddress = RepositoriesAccess.addressRepository.findById(billingAddressId);
			model.addAttribute("billingAddress", billingAddress);
		}
		if ((shippingAddressId != null)) {
			Address shippingAddress = RepositoriesAccess.addressRepository.findById(shippingAddressId);
			model.addAttribute("shippingAddress", shippingAddress);
		}

		EnumPayments[] kindOfPayment = EnumPayments.values();
		String[] paymentName = new String[kindOfPayment.length];
		int i = 0;
		for (EnumPayments x : kindOfPayment)
			paymentName[i++] = x.name();

		model.addAttribute("payments", paymentName);
		model.addAttribute("books", bookNames);

		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("allBooks", books);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<com.shop.data.tables.Orders> orders = RepositoriesAccess.ordersRepository.findAll();

		model.addAttribute("orders", orders);
		return "administratorSite/ordersManager/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("id") String id, Model model) {
		com.shop.data.tables.Orders order = RepositoriesAccess.ordersRepository.findOne(Long.parseLong(id));

		if (order == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/ordersManager/read";
		}
		model.addAttribute("order", order);
		return "administratorSite/ordersManager/read";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
		model.addAttribute("orders", orders);
		return "administratorSite/ordersManager/update";
	}

	@RequestMapping(value = "updateOrder/{orderId}")
	public String updateBook(@PathVariable Long orderId, Model model) {
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
		
		if(foundOrder.getPaymentMethod() != null)
			model.addAttribute("orderPayment", foundOrder.getPaymentMethod().toString());
		
		model.addAttribute("payments", paymentName);
		model.addAttribute("order", foundOrder);
		return "/administratorSite/ordersManager/updateOneOrder";
	}
	
	
	
	@RequestMapping("updateOrder/update")
	public String updateBook(@RequestParam("shippingAddressStreet") String shippingAddressStreet,
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
		//System.out.println(order.getBooks());

		//if(order.getPaymentMethod() != null)
		//	model.addAttribute("orderPayment", order.getPaymentMethod().toString());
		return updateBook(order.getId(), model);
	}
	
	
	@RequestMapping("updateOrder/createBooks123")
	public String create1(@RequestParam("orderId") Long orderId, Model model, HttpServletRequest request) {
		
		
		
		Iterable<Books> allBooks = RepositoriesAccess.booksRepository.findAll();
		LinkedList<Books> chosenBooks = new LinkedList<Books>();
		chosenBooks.clear();

		for (Books x : allBooks)
			if (request.getParameter(x.getName()) != null)
				chosenBooks.add(x);

		Orders order = RepositoriesAccess.ordersRepository.findById(orderId);	
		order.setBooks(chosenBooks);
		
		RepositoriesAccess.ordersRepository.save(order);
		
		return updateBook(orderId, model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();

		model.addAttribute("orders", orders);
		return "administratorSite/ordersManager/delete";
	}

	@RequestMapping(value = "deleteOrder/{orderId}")
	public RedirectView deleteB(@PathVariable Long orderId, Model model) {
		Orders foundOrder = RepositoriesAccess.ordersRepository.findById(orderId);

		if (foundOrder == null)
			model.addAttribute("msg", "not found");// wont work for redirectView
		else {
			RepositoriesAccess.ordersRepository.delete(foundOrder);
			model.addAttribute("msg", "Succes, back to delete more");// wont
																		// work
																		// for
																		// redirectView
		}
		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
		model.addAttribute("orders", orders);

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/orders/delete");
	}

	@RequestMapping(value = "deleteOrder")
	public String deleteB(@RequestParam("id") String id, Model model) {
		Orders foundOrder = RepositoriesAccess.ordersRepository.findById(Long.parseLong(id));

		if (foundOrder == null)
			model.addAttribute("msg", "not found");
		else {
			RepositoriesAccess.ordersRepository.delete(foundOrder);
			model.addAttribute("msg", "Succes");
		}

		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
		model.addAttribute("orders", orders);
		return "/administratorSite/ordersManager/delete";
	}
}
