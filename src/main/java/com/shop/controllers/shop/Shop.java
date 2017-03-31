package com.shop.controllers.shop;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;
import com.shop.others.SendEmail;

@Controller
@RequestMapping("shop")
public class Shop {
	
	
	@RequestMapping
	public String start(Model model) {
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);
		return "shopStartPage";
	}
	
	@RequestMapping("{categoryName}")
	public String categorySite(@PathVariable String categoryName, Model model) {
		Categories category = RepositoriesAccess.categoriesRepository.findByName(categoryName);
		model.addAttribute("books", category.getBooks());	
		return "shop/categorySite";
	}
	
	@RequestMapping("categorySite/{id}")
	public RedirectView add(@PathVariable Long id, @RequestParam("number") String number, HttpServletRequest request, Model model) {
		Books book = RepositoriesAccess.booksRepository.findById(id);
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		Categories category = null;
		for(Categories x : categories) {
			for(Books x1 : x.getBooks()) {
				if(x1.getId() == x1.getId()) {
					category = x;
				}
			}
		}
		if(number == null || number.equals(""))
			return new RedirectView(ApplicationConfig.projectName + "shop/" + category.getName());
		

		LinkedList<Books> basketWithAllBooks = (LinkedList<Books>) request.getSession().getAttribute("basketWithAllBooks");
		HashSet<Books> basket = (HashSet<Books>) request.getSession().getAttribute("basket");
		
		boolean is = false;
		for(Books x : basket) 
			if(x.getId() == book.getId())
				is = true;
			
		if(is) {
			for(int i = 0; i < Long.parseLong(number); i++) 
				basketWithAllBooks.add(book);
		} else {
			for(int i = 0; i < Long.parseLong(number); i++) 
				basketWithAllBooks.add(book);
			basket.add(book);
		}
		request.getSession().setAttribute("basketWithAllBooks", basketWithAllBooks);
		request.getSession().setAttribute("basket", basket);
		
		return new RedirectView(ApplicationConfig.projectName + "shop/" + category.getName());
	}
	
	@RequestMapping("basket")
	public String basket(Model model, HttpServletRequest request) {
		LinkedList<Books> basket = (LinkedList<Books>) request.getSession().getAttribute("basketWithAllBooks");
		HashSet<Books> basketMain = (HashSet<Books>) request.getSession().getAttribute("basket");
		BigDecimal price = toCalculate(basket);
		
			
		model.addAttribute("price", price);
		model.addAttribute("basket1", basket);
		model.addAttribute("basket", basketMain);
		return "shop/basket";
	}
	
//	public int toCalculate(LinkedList<Books> basket) {
//		int price = 0;
//		for(Books x : basket) 
//			if(x.getPrice() != null)
//				price += x.getPrice().intValueExact();
//		return price;
//	}
	public BigDecimal toCalculate(LinkedList<Books> basket) {
		BigDecimal price = new BigDecimal("0"); 
		for(Books x : basket) 
			if(x.getPrice() != null)
				price = x.getPrice().add(price);
		return price;
	}
	
	@RequestMapping("delete")
	public RedirectView delete(@RequestParam("id") Long id, @RequestParam("number") int number, HttpServletRequest request, Model model) {
		LinkedList<Books> basketWithAllBooks = (LinkedList<Books>) request.getSession().getAttribute("basketWithAllBooks");
		HashSet<Books> basket = (HashSet<Books>) request.getSession().getAttribute("basket");
		Books book = RepositoriesAccess.booksRepository.findById(id);
				
		if(book == null)
			return new RedirectView(ApplicationConfig.projectName + "shop/basket");			
		
		boolean is = false;
		int howMany = 1;
		int howManyBooks = 0;
		
		for(Books x : basketWithAllBooks) 
			if(x.getId() == book.getId())
				howManyBooks++;
		
		if(howManyBooks < number) 
			number = howManyBooks;

		while(howMany <= number)
			for(int i = 0; i < basketWithAllBooks.size(); i++) {
				if(basketWithAllBooks.get(i).getId() != null && basketWithAllBooks.get(i).getId() == book.getId() && howMany <= number) {
					basketWithAllBooks.remove(basketWithAllBooks.get(i));
					howMany++;
				}
			}

		for(Books x : basketWithAllBooks) 
			if(book.getId() == x.getId())
				is = true;
		
		if(!is)
			for(Books x : basket) {
				if(x.getId() == book.getId()) {
					basket.remove(x);
					break;
				}
			}

		request.getSession().setAttribute("basketWithAllBooks", basketWithAllBooks);
		request.getSession().setAttribute("basket", basket);
		
		model.addAttribute("basketWithAllBooks", basketWithAllBooks);
		model.addAttribute("basket", basket);
		return new RedirectView(ApplicationConfig.projectName + "shop/basket");
	}
	
	@RequestMapping("continue")
	public String goNext(Model model, HttpServletRequest request) {
		LinkedList<Books> basket = (LinkedList<Books>) request.getSession().getAttribute("basketWithAllBooks");
		EnumPayments[] kindOfPayment = EnumPayments.values();
		String[] paymentName = new String[kindOfPayment.length];
		int i = 0;
		for (EnumPayments x : kindOfPayment)
			paymentName[i++] = x.name();

		model.addAttribute("payments", paymentName);
		
		BigDecimal price = toCalculate(basket);		
		model.addAttribute("price", price);
		
		model.addAttribute("basket1", basket);
		model.addAttribute("basket", request.getSession().getAttribute("basket"));
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser"))
			return "shop/options/anonymousUserOrder";
		
		Users user = RepositoriesAccess.usersRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("user", user);		
		return "shop/options/userOrder";
	}
	
	//add for email
	@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("accept")
	public String accept(HttpServletRequest request, Model model,
			@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,
			
			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry,
			@RequestParam("couponCode") String couponCode, 
			@RequestParam("email") String email,
			@RequestParam("payment") Object payment) {
		LinkedList<Books> secondBasket = (LinkedList<Books>) request.getSession().getAttribute("basketWithAllBooks");
		HashSet<Books> basket = (HashSet<Books>) request.getSession().getAttribute("basket");
		BigDecimal price = toCalculate(secondBasket);
		
		EnumPayments[] kindOfPayment = EnumPayments.values();
		EnumPayments paymentType = null;

		for (EnumPayments x : kindOfPayment)
			if (x.name().equals(payment))
				paymentType = x;

		Users user = RepositoriesAccess.usersRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		Address shippingAddress = new Address(shippingAddressStreet, shippingAddressPostalCode, shippingAddressCity, shippingAddressCountry);
		RepositoriesAccess.addressRepository.save(shippingAddress);
		Address billingAddress = new Address(billingAddressStreet, billingAddressPostalCode, billingAddressCity, billingAddressCountry);
		RepositoriesAccess.addressRepository.save(billingAddress);
		
		Orders order = new Orders();
		order.setBillingAddress(shippingAddress);
		order.setShippingAddress(billingAddress);
		
		CouponCodes coupon = RepositoriesAccess.couponCodesRepository.findByCode(couponCode);
		if(coupon != null) {
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
		for(Books x : basket)
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
		SendEmail.sendCode(text, request);
		
		model.addAttribute("success", "success");
		return "shop/options/userOrder";
	}
	
	
	
	@RequestMapping("acceptAnonymous")
	public String acceptAnonymous(HttpServletRequest request, Model model,
			@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,
			
			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry,
			@RequestParam("email") String email,
			@RequestParam("couponCode") String couponCode, 
			@RequestParam("payment") Object payment) {
		LinkedList<Books> secondBasket = (LinkedList<Books>) request.getSession().getAttribute("basketWithAllBooks");
		HashSet<Books> basket = (HashSet<Books>) request.getSession().getAttribute("basket");
		BigDecimal price = toCalculate(secondBasket);
		
		EnumPayments[] kindOfPayment = EnumPayments.values();
		EnumPayments paymentType = null;

		for (EnumPayments x : kindOfPayment)
			if (x.name().equals(payment))
				paymentType = x;

		Address shippingAddress = new Address(shippingAddressStreet, shippingAddressPostalCode, shippingAddressCity, shippingAddressCountry);
		RepositoriesAccess.addressRepository.save(shippingAddress);
		Address billingAddress = new Address(billingAddressStreet, billingAddressPostalCode, billingAddressCity, billingAddressCountry);
		RepositoriesAccess.addressRepository.save(billingAddress);

		Orders order = new Orders();
		order.setBillingAddress(shippingAddress);
		order.setShippingAddress(billingAddress);

		CouponCodes coupon = RepositoriesAccess.couponCodesRepository.findByCode(couponCode);
		if(coupon != null) {
			order.setCouponCodes(coupon);
			RepositoriesAccess.couponCodesRepository.save(coupon);
		}
		order.setPrice(price);
		order.setPaymentMethod(paymentType);
		RepositoriesAccess.ordersRepository.save(order);
		
		String text = "";
		text += "Price to pay : " + price + "\n";
		text += "\nBooks in order :\n";
		for(Books x : basket)
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
		
		SendEmail.whatAmIDoing(text);

		model.addAttribute("success", "success");
		return "shop/options/userOrder";
	}
}
