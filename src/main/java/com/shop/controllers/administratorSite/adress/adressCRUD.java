package com.shop.controllers.administratorSite.adress;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.data.tables.Pictures;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

/*
 * COUPON CODES WITH ADDDRES
 * DELETE BY ID DOEASNT WORK 
 * FIX IT LATER
 */
@Controller
@RequestMapping("administratorSite/address")
public class adressCRUD {

	@RequestMapping
	public String createSit12e() {
		return "administratorSite/adressStartPage";
	}

	@RequestMapping("create")
	public String createSite() {
		return "administratorSite/addressManager/create";
	}

	@RequestMapping("/createAddress")
	public String create1(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country, Model model) {
		Address address = new Address(street, postalCode, city, country);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/addressManager/create";
	}

	@RequestMapping("read")
	public String readSite(Model model) {
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();
		model.addAttribute("allAddress", address);
		return "administratorSite/addressManager/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("id") String id, Model model) {
		Address address = RepositoriesAccess.addressRepository.findById(Long.parseLong(id));

		if (address == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/addressManager/read";
		}
		model.addAttribute("address", address);
		return "administratorSite/addressManager/read";
	}

	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("allAddress", address);
		return "administratorSite/addressManager/update";
	}

	@RequestMapping("updateAddress/update")
	public String updateBook(@RequestParam("city") String city, @RequestParam("country") String country,
			@RequestParam("postalCode") String postalCode, @RequestParam("street") String street,
			@RequestParam("id") String id, Model model) {

		Address address = RepositoriesAccess.addressRepository.findById(Long.parseLong(id));

		if (address == null) {
			model.addAttribute("msg", "not found category to update");
			return "administratorSite/couponCodesManager/updateOneCategory";
		}

		address.setCity(city);
		address.setCountry(country);
		address.setPostalCode(postalCode);
		address.setStreet(street);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);
		model.addAttribute("msg", "Success");
		return "administratorSite/addressManager/updateOneCategory";
	}

	@RequestMapping(value = "updateAddress/{couponCodesId}")
	public String updateBook(@PathVariable Long couponCodesId, Model model) {
		Address address = RepositoriesAccess.addressRepository.findById(couponCodesId);

		if (address == null)
			model.addAttribute("msg", "not found");

		model.addAttribute("address", address);
		return "/administratorSite/addressManager/updateOneCategory";
	}

	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("allAddress", address);
		return "administratorSite/addressManager/delete";
	}

	@RequestMapping(value = "deleteAddress/{addressId}")
	public RedirectView deleteB(@PathVariable Long addressId, Model model) {
		Address address = RepositoriesAccess.addressRepository.findById(addressId);

		if (address == null)
			model.addAttribute("msg", "not found");// wont work for redirectView
		else {
			Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
			Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();

			for (Orders x : orders)
				if (x.getBillingAddress() != null)
					if (x.getBillingAddress().getId() == address.getId()) {
						x.setBillingAddress(null);
						RepositoriesAccess.ordersRepository.save(x);
					}

			for (Orders x : orders)
				if (x.getShippingAddress() != null)
					if (x.getShippingAddress().getId() == address.getId()) {
						x.setShippingAddress(null);
						RepositoriesAccess.ordersRepository.save(x);
					}

			for (Users x : users)
				if (x.getAddress() != null)
					if (x.getAddress().getId() == address.getId()) {
						x.setAddress(null);
						RepositoriesAccess.userRolesRepository.save(x);
					}

			RepositoriesAccess.addressRepository.delete(address);
			model.addAttribute("msg", "Succes, back to delete more");// wont
																		// work
																		// for
																		// redirectView
		}
		Iterable<Address> allAddress = RepositoriesAccess.addressRepository.findAll();
		model.addAttribute("allAddress", allAddress);

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/address/delete");
		// for (Iterator<Orders> iterator = orders.iterator();
		// iterator.hasNext();) {
		// Orders order = iterator.next();
		// System.out.println((order.getBillingAddress().getId() ==
		// address.getId()));
		// if (order.getBillingAddress() != null) {
		// System.out.println("halo1");
		// if (order.getBillingAddress().getId() == address.getId()) {
		// System.out.println("halo1");
		// order.setCouponCodes(null);
		// RepositoriesAccess.ordersRepository.save(order);
		// }
		// }
		// }
		// for (Iterator<Orders> iterator = orders.iterator();
		// iterator.hasNext();) {
		// Orders order = iterator.next();
		// if (order.getShippingAddress() != null)
		// if (order.getShippingAddress().getId() == address.getId()) {
		// order.setCouponCodes(null);
		// RepositoriesAccess.ordersRepository.save(order);
		// }
		// }
		// for (Iterator<Users> iterator = users.iterator();
		// iterator.hasNext();) {
		// Users user = iterator.next();
		// if (user.getAddress() != null)
		// if (user.getAddress().getId() == address.getId()) {
		// user.setAddress(null);
		// RepositoriesAccess.userRolesRepository.save(user);
		// }
		// }
	}

	@RequestMapping(value = "deleteAddress")
	public String deletbveB(@RequestParam("id") Long id, Model model) {
		Address address = RepositoriesAccess.addressRepository.findById(id);

		if (address == null)
			model.addAttribute("msg", "not found");
		else {
			Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
			Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();

			for (Orders x : orders)
				if (x.getBillingAddress() != null)
					if (x.getBillingAddress().getId() == address.getId()) {
						x.setBillingAddress(null);
						RepositoriesAccess.ordersRepository.save(x);
					}

			for (Orders x : orders)
				if (x.getShippingAddress() != null)
					if (x.getShippingAddress().getId() == address.getId()) {
						x.setShippingAddress(null);
						RepositoriesAccess.ordersRepository.save(x);
					}

			for (Users x : users)
				if (x.getAddress() != null)
					if (x.getAddress().getId() == address.getId()) {
						x.setAddress(null);
						RepositoriesAccess.userRolesRepository.save(x);
					}

			RepositoriesAccess.addressRepository.delete(address);
			model.addAttribute("msg", "Succes");
		}
		Iterable<Address> allAdress = RepositoriesAccess.addressRepository.findAll();
		model.addAttribute("allAddress", allAdress);
		return "/administratorSite/addressManager/delete";
	}
}
