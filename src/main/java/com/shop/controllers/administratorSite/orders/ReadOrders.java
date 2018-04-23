package com.shop.controllers.administratorSite.orders;

import com.shop.data.services.OrdersService;
import com.shop.data.tables.Order;
import com.shop.others.RepositoriesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("administratorSite/orders")
public class ReadOrders {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String readSite(Model model, HttpServletRequest request) {
        Iterable<Order> orders = ordersService.findAll();

        model.addAttribute("orders", orders);
        return "administratorSite/ordersManager/read";
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public String readOne(@RequestParam("id") String id, Model model) {
        Iterable<Order> orders = ordersService.findAll();
        Order order = ordersService.findOne(Long.parseLong(id));

        if (order == null) {
            model.addAttribute("msg", "not found");
            model.addAttribute("orders", orders);
            return "administratorSite/ordersManager/read";
        }
        model.addAttribute("order", order);
        return "administratorSite/ordersManager/read";
    }
}
