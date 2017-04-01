package com.shop.controllers.administratorSite.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("administratorSite/users")
public class UsersSite {

	@RequestMapping
	public String start() {
		return "administratorSite/usersStartPage";
	}
}
