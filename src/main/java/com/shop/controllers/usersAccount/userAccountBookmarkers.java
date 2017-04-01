package com.shop.controllers.usersAccount;

import java.util.Iterator;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
@Controller
@RequestMapping("/account")
public class userAccountBookmarkers {

	@RequestMapping
	public String start() {
		return "userAccount/userAccount";
	}

	@RequestMapping("orders")
	public String ordesBookmarker(Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("orders", user.getOrders());
		return "userAccount/options/orders";
	}

	@RequestMapping("changeData")
	public String changeDataBookmarker(Model model) {
		Users user1 = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());

		model.addAttribute("user", user);
		return "userAccount/options/changeData";
	}

	@RequestMapping("changeEmail")
	public String changeEmailBookmarker(Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "userAccount/options/changeEmail";
	}

	@RequestMapping("changePasswd")
	public String changePasswordBookmarker(Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "userAccount/options/changePassword";
	}
	
	@RequestMapping("informations")
	public String userInformations(Model model) {
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
}
