package com.shop.controllers.administratorSite.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/users")
public class DeleteUsers {

	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		Iterable<UserRole> roles = RepositoriesAccess.userRolesRepository.findAll();

		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "administratorSite/usersManager/delete";
	}

	@RequestMapping(value = "deleteUser/{userId}")
	public RedirectView deleteFromButton(@PathVariable Long userId, Model model, RedirectAttributes red) {
		Users foundUser = RepositoriesAccess.usersRepository.findById(userId);

		if (foundUser == null)
			red.addFlashAttribute("msg", "not found");
		else {
			RepositoriesAccess.usersRepository.delete(foundUser);
			red.addFlashAttribute("msg", "Succes, back to delete more");
		}
		Iterable<Users> users = RepositoriesAccess.usersRepository.findAll();
		model.addAttribute("users", users);

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/users/delete");
	}

	@RequestMapping(value = "deleteUser")
	public String deleteFromInputText(@RequestParam("userName") String userName, Model model) {
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
}
