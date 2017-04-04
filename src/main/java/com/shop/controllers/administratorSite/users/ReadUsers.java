package com.shop.controllers.administratorSite.users;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Address;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/users")
public class ReadUsers {

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("address", address);
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "administratorSite/usersManager/read";
	}

	@RequestMapping(value = "read", method = RequestMethod.POST)
	public String readOne(@RequestParam("login") String login, Model model) {
		Users users = RepositoriesAccess.usersRepository.findByLogin(login);
		Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();

		if (users == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/usersManager/read";
		}

		model.addAttribute("roles", roles);
		model.addAttribute("user", users);
		model.addAttribute("address", users.getAddress());
		return "administratorSite/usersManager/read";
	}
}
