package com.shop.controllers.administratorSite.users;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Address;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/users")
public class UpdateUsers {

	@RequestMapping("/update")
	public String update(Model model) {
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("address", address);
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "administratorSite/usersManager/update";
	}

	@RequestMapping(value = "updateUser/{userId}")
	public String updateBook(@PathVariable Long userId, Model model, HttpServletRequest request) {
		Users foundUser = RepositoriesAccess.usersRepository.findById(userId);

		if (foundUser == null)
			model.addAttribute("msg", "not found");

		model.addAttribute("user", foundUser);
		model.addAttribute("address", foundUser.getAddress());
		return "/administratorSite/usersManager/updateOneUser";
	}

	@RequestMapping("updateUser/update")
	public String updateBook(@RequestParam("id") String id, @RequestParam("login") String login,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("surname") String surname, @RequestParam("eMail") String eMail,
			@RequestParam(name = "addressId", required = false) Long addressId, Model model,
			HttpServletRequest request) {

		Address address = null;

		if (addressId != null)
			address = RepositoriesAccess.addressRepository.findById(addressId);
		Users foundUser = RepositoriesAccess.usersRepository.findById(Long.parseLong(id));

		if (foundUser == null) {
			model.addAttribute("book", foundUser);
			model.addAttribute("msg", "not found book to update");
			return "administratorSite/usersManager/updateOneBook";
		}
		if (address == null)
			model.addAttribute("msg", "not found address");
		else
			foundUser.setAddress(address);

		String adminRole = request.getParameter("Admin");
		String userRole = request.getParameter("User");

		foundUser.setName(name);
		foundUser.setLogin(login);
		foundUser.setPassword(password);
		foundUser.setName(name);
		foundUser.setSurname(surname);
		foundUser.seteMail(eMail);
		// foundUser.setDateBirth(dateBirth);
		if (request.getParameter("Admin") != null)
			addUserWithRoles(adminRole, foundUser);
		else if (request.getParameter("User") != null)
			addUserWithRoles(userRole, foundUser);
		else
			RepositoriesAccess.usersRepository.save(foundUser);
		model.addAttribute("msg", "success");
		model.addAttribute("user", foundUser);
		model.addAttribute("address", address);
		return "administratorSite/usersManager/updateOneUser";
	}

	@RequestMapping("updateUser/createAddress")
	public String createAddress(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country,
			@RequestParam("userId") Long userId, Model model) {
		Users user = RepositoriesAccess.usersRepository.findOne(userId);
		Address address = new Address(street, postalCode, city, country);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);
		model.addAttribute("user", user);

		return "/administratorSite/usersManager/updateOneUser";
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
