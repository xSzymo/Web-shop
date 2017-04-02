package com.shop.controllers.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller
public class ResetPassword {
	@RequestMapping("reset")
	public String resetPassword(@RequestParam(name = "password") String password, Model model,
			HttpServletRequest request) {
		String eMail = (String) request.getSession().getAttribute("email");

		if ((boolean) request.getSession().getAttribute("authorize") == true) {
			Users user = RepositoriesAccess.usersRepository.findByeMail(eMail);
			user.setPassword(password);
			RepositoriesAccess.usersRepository.save(user);

			model.addAttribute("msg", "Success");	
			model.addAttribute("Success", "Success");			
			request.getSession().removeAttribute("code");
			request.getSession().removeAttribute("authorize");
			request.getSession().removeAttribute("email");
		} else
			model.addAttribute("msg", "Something went wrong");						
		return "loginAndRegistration/reset/resetPassword";
	}

	@RequestMapping("resetPassword")
	public String codeSite(@RequestParam(name = "code") String code, HttpServletResponse response,
			HttpServletRequest request, Model model) {

		boolean codeAccepted = false;

		if (request.getSession().getAttribute("code").equals(code)) {
			codeAccepted = true;
			request.getSession().setAttribute("authorize", true);
		} else {
			model.addAttribute("msg", "Woops, wrong code");
			request.getSession().setAttribute("authorize", false);
		}
		model.addAttribute("codeAccepted", codeAccepted);

		return "loginAndRegistration/reset/resetPassword";
	}

	public String findCookie(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals("code"))
					return cookie.getValue();
		return "-1";
	}
}
