package com.shop.controllers.administratorSite.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administratorSite/orders")
public class OrdersSite {

	@RequestMapping
	public String start() {
		return "administratorSite/ordersStartPage";
	}
}
