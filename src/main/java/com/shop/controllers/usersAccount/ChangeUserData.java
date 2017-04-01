package com.shop.controllers.usersAccount;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;
import com.shop.others.SendEmail;

@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
@Controller
@RequestMapping("/account")
public class ChangeUserData {

	@RequestMapping("changePassword")
	public String changePassword(@RequestParam("password") String password, @RequestParam("password1") String password1,
			Model model) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (password.equals(password1))
			return SendEmail.sendCode123(user, model, password);
		else
			return "userAccount/options/changePassword";
	}

	@RequestMapping("changeAccEmail")
	public String changeEmail(@RequestParam("eMail") String eMail, Model model, HttpServletRequest request) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		SendEmail.sendEmail(user, eMail, model, request);
		model.addAttribute("eMail", eMail);
		return "userAccount/options/changeEmailWithCode";
	}

	@RequestMapping("changeEmailCode")
	public String accepNewEmail(@RequestParam("eMail") String eMail, @RequestParam("code") String code, Model model,
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
