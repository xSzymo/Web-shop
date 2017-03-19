package com.shop.controllers.administratorSite.users;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.operations.CustomUserDetails;
import com.shop.data.tables.Address;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Pictures;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

/*
 * add a few picture to 1 book
 * 2.make http request with diffrent url which should be
 * 
 * when 2 box are checked update is crashing - check it later
 */
@Controller
@RequestMapping("administratorSite/users")
public class UsersCRUD {

	@RequestMapping
	public String start() {
		return "administratorSite/usersStartPage";
	}

	@RequestMapping("create")
	public String createSite() {
		return "administratorSite/usersManager/create";
	}

	@RequestMapping("/createUser")
	public String create1(@RequestParam("login") String login, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("eMail") String eMail, @RequestParam("address") Long addressId, Model model,
			HttpServletRequest request) {

		Users foundUser = RepositoriesAccess.usersRepository.findByLogin(login);
		Address address = RepositoriesAccess.addressRepository.findById(addressId);

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
	public String create1(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country, Model model) {
		Address address = new Address(street, postalCode, city, country);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);

		return "administratorSite/usersManager/create";
	}

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("address", address);
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "administratorSite/usersManager/read";
	}

	@RequestMapping("readOne")
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

	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("address", address);
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "administratorSite/usersManager/update";
	}

	@RequestMapping("updateUser/update")
	public String updateBook(@RequestParam("id") String id, @RequestParam("login") String login,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("surname") String surname, @RequestParam("eMail") String eMail,
			@RequestParam("addressId") Long addressId, Model model, HttpServletRequest request) {
		
		Address address = null;
		
		if(addressId != null)
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
	public String creatcxze1(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country,
			@RequestParam("userId") Long userId, Model model) {
		Users user = RepositoriesAccess.usersRepository.findOne(userId);	
		Address address = new Address(street, postalCode, city, country);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);
		model.addAttribute("user", user);

		return "/administratorSite/usersManager/updateOneUser";
	}

	public boolean diffrentMethod(String role, Users user) {
		Iterable<UserRole> found = RepositoriesAccess.userRolesRepository.findAll();

		for (UserRole x : found)
			for (Iterator<Users> iterator = x.getUser().iterator(); iterator.hasNext();) {
				Users a = iterator.next();
				if (a.getId() == user.getId()) {
					x.getUser().remove(user);
					RepositoriesAccess.userRolesRepository.save(x);
				}
			}

		for (UserRole x : found)
			if (x.getRole().equals(role)) {
				x.getUser().add(user);
				RepositoriesAccess.userRolesRepository.save(x);
				return true;
			}
		return false;
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

	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "administratorSite/usersManager/delete";
	}

	@RequestMapping(value = "deleteUser/{userId}")
	public RedirectView deleteB(@PathVariable Long userId, Model model) {
		Users foundUser = RepositoriesAccess.usersRepository.findById(userId);

		if (foundUser == null)
			model.addAttribute("msg", "not found");// wont work for redirectView
		else {
			RepositoriesAccess.usersRepository.delete(foundUser);
			model.addAttribute("msg", "Succes, back to delete more");// wont
																		// work
																		// for
																		// redirectView
		}
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		model.addAttribute("users", users);

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/users/delete");
	}

	@RequestMapping(value = "deleteUser")
	public String deleteB(@RequestParam("userName") String userName, Model model) {
		Users foundUser = RepositoriesAccess.usersRepository.findByLogin(userName);

		if (foundUser == null)
			model.addAttribute("msg", "not found");
		else {
			RepositoriesAccess.usersRepository.delete(foundUser);
			model.addAttribute("msg", "Succes");
		}

		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		model.addAttribute("users", users);
		return "/administratorSite/usersManager/delete";
	}

	public boolean addUserWithRoles(String role, Users user) {
		Iterable<UserRole> found = RepositoriesAccess.userRolesRepository.findAll();

		UserRole ROLEPLAYING = null;

		for (UserRole x : found)
			if (x.getRole().equals(role)) {
				for(Users x1: x.getUser()) {
					if(x1.getId() == user.getId())
						RepositoriesAccess.usersRepository.save(user);
						return false;
				}
				x.getUser().add(user);
				RepositoriesAccess.userRolesRepository.save(x);
				return true;
			}
		return false;
	}
}
