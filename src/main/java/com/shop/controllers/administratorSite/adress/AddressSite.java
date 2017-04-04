package com.shop.controllers.administratorSite.adress;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("administratorSite/address")
public class AddressSite {

	@RequestMapping(method = RequestMethod.GET)
	public String start() {
		return "administratorSite/adressStartPage";
	}
}
