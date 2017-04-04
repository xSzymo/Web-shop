package com.shop.controllers.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.operations.UserDAO;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller("log")
public class Login {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginSite() {
		return "loginAndRegistration/login";
	}

	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String registrationSite() {
		return "loginAndRegistration/registration";
	}

	@RequestMapping(value = "forgotPassword", method = RequestMethod.GET)
	public String forgotPasswordSite() {
		return "loginAndRegistration/reset/forgotPassword";
	}

	@RequestMapping(value = "forgotUsername", method = RequestMethod.GET)
	public String forgotUsernameSite() {
		return "loginAndRegistration/reset/forgotUsername";
	}

	@RequestMapping(value = "codePassword", method = RequestMethod.GET)
	public String codePassword() {
		return "loginAndRegistration/reset/codePassword";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginUser(@RequestParam(name = "login", required = false, defaultValue = "") String login,
			@RequestParam(name = "password", required = false, defaultValue = "") String password,
			HttpServletRequest request, Model model) {
		if (login == null || login.equals("") && password == null || password.equals(""))
			return "loginAndRegistration/loginFailed";

		if (UserDAO.isUser(login, password)) {
			UserDAO.login(login, password);
			if (SecurityContextHolder.getContext().getAuthentication().getCredentials().equals("admin"))
				return "administratorStartPage";
			else
				return "userAccount/userAccount";
		} else {
			model.addAttribute("msg", "Wrong password or username, check it again !");
			return "loginAndRegistration/login";
		}
	}

	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public String userRegistration(@RequestParam(name = "login", required = false, defaultValue = "") String login,
			@RequestParam(name = "password", required = false, defaultValue = "") String password,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "surname", required = false) String surname,
			@RequestParam(name = "eMail", required = false, defaultValue = "") String eMail,
			@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "postalCode", required = false) String postalCode,
			@RequestParam(name = "street", required = false) String street,
			@RequestParam(name = "date", required = false) String date, HttpServletRequest request, Model model) {

		if (login.equals("") || login == null || password.equals("") || password == null || eMail.equals("")
				|| eMail == null) {
			model.addAttribute("msg", "You have to at least input login, password and e-mail");
			return "loginAndRegistration/registration";
		}

		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();

		for (Users x : users) {
			if (x.getLogin() != null)
				if (x.getLogin().equals(login)) {
					model.addAttribute("msg", "That user name allready exist  !");
					return "loginAndRegistration/registration";
				}
			if (x.geteMail() != null)
				if (x.geteMail().equals(eMail)) {
					model.addAttribute("msg", "That user e-mail allready exist  !");
					return "loginAndRegistration/registration";
				}
		}

		UserDAO.register(login, password, eMail, name, surname, street, country, city, postalCode, date);

		model.addAttribute("msg", "Successful registration  !");
		return "loginAndRegistration/login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(Model model) {
		UserDAO.logout();
		model.addAttribute("logged", false);
		return "start";
	}
}
