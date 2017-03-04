package com.shop.controllers.login;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.ui.Model;

import com.shop.data.operations.CustomUserDetails;
import com.shop.data.operations.UserDAO;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller("log")
public class LoginActions {

	@RequestMapping("login")
	public String loginSite() {
		return "loginAndRegistration/login";
	}
	
	@RequestMapping("registration")
	public String registrationSite() {
		return "loginAndRegistration/registration";
	}

	@RequestMapping("forgotPassword")
	public String forgotPasswordSite() {
		return "loginAndRegistration/reset/forgotPassword";
	}

	@RequestMapping("forgotUsername")
	public String forgotUsernameSite() {
		return "loginAndRegistration/reset/forgotUsername";
	}
	
	@RequestMapping("codePassword")
	public String codePassword() {
		return "loginAndRegistration/reset/codePassword";
	}

	@RequestMapping(value = "userLogin", method = RequestMethod.GET)
	public String loginUser(@RequestParam(name = "login", required = false, defaultValue = "") String login,
			@RequestParam(name = "password", required = false, defaultValue = "") String password,
			HttpServletRequest request, Model model) {

		if (login == null || login.equals("") || password == null || password.equals(""))
			return "loginAndRegistration/loginFailed";

		if (UserDAO.isUser(login, password)) {
			UserDAO.login(login, password);	
			return "start";
		} else {
			model.addAttribute("msg", "Wrong password or username, check it again !");
			return "loginAndRegistration/login";
		}
	}	
	
	@RequestMapping(value = "userRegistration", method = RequestMethod.POST)
	public String userRegistration(@RequestParam(name = "login", required = false, defaultValue = "") String login,
			@RequestParam(name = "password", required = false, defaultValue = "") String password,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "surname", required = false) String surname,
			@RequestParam(name = "eMail", required = false, defaultValue = "") String eMail,
			@RequestParam(name = "dateBirth", required = false) String dateBirth,
			@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "postalCode", required = false) String postalCode,
			@RequestParam(name = "street", required = false) String street, HttpServletRequest request, Model model) {

		if (login.equals("") || login == null || password.equals("") || password == null || eMail.equals("")
				|| eMail == null) {
			model.addAttribute("msg", "You have to at least input login, password and e-mail");
			return "loginAndRegistration/registration";
		}

		if (name.equals("optional")) name = null;
		if (surname.equals("optional")) surname = null;
		if (dateBirth.equals("optional")) dateBirth = null;
		if (country.equals("optional")) country = null;
		if (city.equals("optional")) city = null;
		if (postalCode.equals("optional")) postalCode = null;
		if (street.equals("optional")) street = null;

		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();

		for (Users x : users) {
			if (x.getLogin().equals(login)) {
				model.addAttribute("msg", "That user name allready exist  !");
				return "loginAndRegistration/registration";
			}
			if (x.geteMail().equals(eMail)) {
				model.addAttribute("msg", "That user e-mail allready exist  !");
				return "loginAndRegistration/registration";
			}
		}

		UserDAO.register(login, password, eMail, name, surname, dateBirth, street, country, city, postalCode);

		model.addAttribute("msg", "Successful registration  !");
		return "loginAndRegistration/login";
	}

	@RequestMapping(value = "logout")
	public String logout() {
		UserDAO.logout();
		return "start";
	}
}
