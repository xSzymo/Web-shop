package com.shop.controllers.administratorSite.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("administratorSite/orders")
public class OrdersSite {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String start() {
        return "administratorSite/ordersStartPage";
    }

}
