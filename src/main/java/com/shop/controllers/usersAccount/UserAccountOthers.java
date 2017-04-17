package com.shop.controllers.usersAccount;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.controllers.login.Login;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import com.shop.others.email.SendEmailDeleteAccount;

@Controller
@RequestMapping("/account")
public class UserAccountOthers {

	@RequestMapping(value = "informations", method = RequestMethod.GET)
	public String userInformations(Model model) {
		User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());
		Iterable<UserRole> found = RepositoriesAccess.userRolesRepository.findAll();

		UserRole ROLEPLAYING = null;

		for (UserRole x : found)
			for (Iterator<User> iterator = x.getUser().iterator(); iterator.hasNext();) {
				User a = iterator.next();
				if (a.getId() == user.getId()) {
					ROLEPLAYING = x;
				}
			}

		model.addAttribute("role", ROLEPLAYING);
		model.addAttribute("user", user);
		return "userAccount/options/informations";
	}

	@RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
	public String deleteAccount(@RequestParam("password") String password, @RequestParam("password1") String password1,
			Model model, HttpServletRequest request) {
		User user = RepositoriesAccess.usersRepository
				.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		if (password.equals(password1))
			if (user.getPassword().equals(password))
				SendEmailDeleteAccount.sendCode(user, request);

		return "userAccount/options/deletAccount";
	}

	@RequestMapping(value = "deleteAccountCode/{code}", method = RequestMethod.GET)
	public String deleteAccountWithCode(@PathVariable String code, HttpServletRequest request, HttpServletResponse response, Model model) {

		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		if (request.getSession().getAttribute("userName").equals(login))
			if (request.getSession().getAttribute("deleteAccountCode").equals(code)) {
				User user = RepositoriesAccess.usersRepository.findByLogin(login);
				RepositoriesAccess.usersRepository.delete(user.getId());
			}
		return Login.logout(request, response, model);
	}
}
