package com.shop.controllers.administratorSite.orders;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Orders;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/orders")
public class ReadOrders {

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();

		model.addAttribute("orders", orders);
		return "administratorSite/ordersManager/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("id") String id, Model model) {
		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
		Orders order = RepositoriesAccess.ordersRepository.findOne(Long.parseLong(id));

		if (order == null) {
			model.addAttribute("msg", "not found");
			model.addAttribute("orders", orders);
			return "administratorSite/ordersManager/read";
		}
		model.addAttribute("order", order);
		return "administratorSite/ordersManager/read";
	}
}
