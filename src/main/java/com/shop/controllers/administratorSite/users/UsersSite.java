package com.shop.controllers.administratorSite.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("administratorSite/users")
public class UsersSite {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String start() {
		return "administratorSite/usersStartPage";
	}
}
