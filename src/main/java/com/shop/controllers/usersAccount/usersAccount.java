package com.shop.controllers.usersAccount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.shop.data.tables.Address;
import com.shop.data.tables.Orders;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;
import com.shop.others.SendEmail;

@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
@Controller
@RequestMapping("/account")
public class usersAccount {

	private Address a;

	@RequestMapping
	public String halo1() {
		return "userAccount/userAccount";
	}

	@RequestMapping("orders")
	public String halosd1(Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("orders", user.getOrders());
		return "userAccount/options/orders";
	}

	@RequestMapping("changeData")
	public String halos123d1(Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "userAccount/options/changeData";
	}

	@RequestMapping("changeEmail")
	public String halos1as23d1(Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "userAccount/options/changeEmail";
	}

	@RequestMapping("changePasswd")
	public String halos1fgs23d1(Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "userAccount/options/changePassword";
	}

	@RequestMapping("informations")
	public String halosd1cxz(Model model) {
		Users user1 = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());
		Iterable<UserRole> found = RepositoriesAccess.userRolesRepository.findAll();

		UserRole ROLEPLAYING = null;

		for (UserRole x : found)
			for (Iterator<Users> iterator = x.getUser().iterator(); iterator.hasNext();) {
				Users a = iterator.next();
				if (a.getId() == user.getId()) {
					ROLEPLAYING = x;
				}
			}

		model.addAttribute("role", ROLEPLAYING);
		model.addAttribute("user", user);
		return "userAccount/options/informations";
	}

	@RequestMapping(value = "info/{user}")
	public String halo() {
		return "userAccount/userAccount";
	}

	@RequestMapping("update")
	public String updateBook(@RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("addressId") Long addressId, @RequestParam("street") String street,
			@RequestParam("postalCode") String postalCode, @RequestParam("city") String city,
			@RequestParam("country") String country, Model model, HttpServletRequest request) {
		Users user1 = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());
		Address address = null;

		if (addressId != null)
			address = RepositoriesAccess.addressRepository.findById(addressId);
		if (address == null)
			address = new Address();

		address.setCity(city);
		address.setCountry(country);
		address.setPostalCode(postalCode);
		address.setStreet(street);
		user.setAddress(address);
		RepositoriesAccess.addressRepository.save(address);

		user.setName(name);
		user.setSurname(surname);
		// foundUser.setDateBirth(dateBirth);
		RepositoriesAccess.usersRepository.save(user);

		model.addAttribute("msg", "success");
		model.addAttribute("user", user);
		model.addAttribute("address", address);
		return "userAccount/options/changeData";
	}

	@RequestMapping("createAddress")
	public String creatcxze1(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country, Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Address address = new Address(street, postalCode, city, country);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);
		model.addAttribute("user", user);

		return "userAccount/options/changeData";
	}

	@RequestMapping("changePassword")
	public String halos1fgs2v3d1(@RequestParam("password") String password, @RequestParam("password1") String password1,
			Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (password.equals(password1))
			return SendEmail.sendCode123(user, model, password);
		else
			return "userAccount/options/changePassword";
	}

	@RequestMapping("changeAccEmail")
	public String halos1asf2g23d1(@RequestParam("eMail") String eMail, Model model, HttpServletRequest request) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		SendEmail.sendEmail(user, eMail, model, request);
		model.addAttribute("eMail", eMail);
		return "userAccount/options/changeEmailWithCode";
	}

	@RequestMapping("changeEmailCode")
	public String halos1asfg23d1(@RequestParam("eMail") String eMail, @RequestParam("code") String code, Model model,
			HttpServletRequest request) {
		Users user1 = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (request.getSession().getAttribute("code").equals(code)) {
			model.addAttribute("msg", "success");
			Users user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());
			user.seteMail(eMail);
			RepositoriesAccess.usersRepository.save(user);
			request.getSession().removeAttribute("code");
			model.addAttribute("eMail", eMail);
		} 
			model.addAttribute("msg", "wrong code");
		model.addAttribute("code", request.getSession().getAttribute("code"));
		model.addAttribute("eMail", eMail);

		model.addAttribute("eMail", eMail);
		return "userAccount/options/changeEmailWithCode";
	}
}
