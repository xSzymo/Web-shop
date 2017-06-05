package com.shop.controllers.administratorSite.orders;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Order;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("administratorSite/orders")
public class DeleteOrders {

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteSite(Model model) {
        Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();

        model.addAttribute("orders", orders);
        return "administratorSite/ordersManager/delete";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public RedirectView deleteFromButton(@PathVariable Long id, Model model, RedirectAttributes red) {
        Order foundOrder = RepositoriesAccess.ordersRepository.findById(id);

        if (foundOrder == null)
            red.addFlashAttribute("msg", "not found");
        else {
            RepositoriesAccess.ordersRepository.delete(foundOrder);
            red.addFlashAttribute("msg", "Succes");
        }
        Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();
        model.addAttribute("orders", orders);

        return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/orders/delete");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteFromInputText(@RequestParam("id") String id, Model model) {
        Order foundOrder = RepositoriesAccess.ordersRepository.findById(Long.parseLong(id));

        if (foundOrder == null)
            model.addAttribute("msg", "not found");
        else {
            RepositoriesAccess.ordersRepository.delete(foundOrder);
            model.addAttribute("msg", "Succes");
        }

        Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();
        model.addAttribute("orders", orders);
        return "/administratorSite/ordersManager/delete";
    }
}
