package com.shop.controllers.administratorSite.adress;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administratorSite/address")
public class AddressSite {
	
	@RequestMapping
	public String start() {
		return "administratorSite/adressStartPage";
	}
}
