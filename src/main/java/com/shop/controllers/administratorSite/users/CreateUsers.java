package com.shop.controllers.administratorSite.users;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Address;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/users")
public class CreateUsers {

	@RequestMapping("create")
	public String createSite() {
		return "administratorSite/usersManager/create";
	}

	@RequestMapping("/createUser")
	public String create(@RequestParam("login") String login, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("eMail") String eMail, @RequestParam("address") Long addressId, Model model,
			HttpServletRequest request) {

		Users foundUser = RepositoriesAccess.usersRepository.findByLogin(login);

		Address address = null;
		if (addressId != null)
			address = RepositoriesAccess.addressRepository.findById(addressId);

		String adminRole = request.getParameter("Admin");
		String userRole = request.getParameter("User");

		if (foundUser != null) {
			model.addAttribute("msgError", "That user already exist");
			return "administratorSite/usersManager/create";
		}
		Users user = new Users(login, password, name, surname, eMail);

		if (address == null) {
			model.addAttribute("msgError", "No address found(optional)");
		} else
			user.setAddress(address);

		if (request.getParameter("Admin") != null)
			addUserWithRoles(adminRole, user);
		else if (request.getParameter("User") != null)
			addUserWithRoles(userRole, user);
		else
			RepositoriesAccess.usersRepository.save(user);

		model.addAttribute("msgSuccess", "success");
		return "administratorSite/usersManager/create";
	}

	@RequestMapping("/createAddress")
	public String createAddress(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country, Model model) {
		Address address = new Address(street, postalCode, city, country);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);

		return "administratorSite/usersManager/create";
	}

	public void addUserWithRoles(String role, Users user) {
		Iterable<UserRole> users = RepositoriesAccess.userRolesRepository.findAll();
		for (UserRole x : users)
			if (x.getRole().equals(role)) {
				for (Users x1 : x.getUser()) {
					if (x1.getId() == user.getId()) {
						RepositoriesAccess.usersRepository.save(user);
						return;
					}
				}
				x.getUser().add(user);
				RepositoriesAccess.userRolesRepository.save(x);
				return;
			}
	}
}
