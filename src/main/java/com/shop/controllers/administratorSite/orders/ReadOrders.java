package com.shop.controllers.administratorSite.orders;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Order;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/orders")
public class ReadOrders {

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();

		model.addAttribute("orders", orders);
		return "administratorSite/ordersManager/read";
	}

	@RequestMapping(value = "read", method = RequestMethod.POST)
	public String readOne(@RequestParam("id") String id, Model model) {
		Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();
		Order order = RepositoriesAccess.ordersRepository.findOne(Long.parseLong(id));

		if (order == null) {
			model.addAttribute("msg", "not found");
			model.addAttribute("orders", orders);
			return "administratorSite/ordersManager/read";
		}
		model.addAttribute("order", order);
		return "administratorSite/ordersManager/read";
	}
}
