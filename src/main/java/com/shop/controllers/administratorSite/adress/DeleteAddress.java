package com.shop.controllers.administratorSite.adress;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Address;
import com.shop.data.tables.Order;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("administratorSite/address")
public class DeleteAddress {

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteSite(Model model) {
        Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

        model.addAttribute("address", address);
        return "administratorSite/addressManager/delete";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public RedirectView deleteFromButton(@PathVariable Long id, Model model) {
        Address address = RepositoriesAccess.addressRepository.findById(id);

        if (address != null) {
            Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();
            Iterable<User> users = RepositoriesAccess.usersRepository.findAll();

            for (Order x : orders)
                if (x.getBillingAddress() != null)
                    if (x.getBillingAddress().getId() == address.getId()) {
                        x.setBillingAddress(null);
                        RepositoriesAccess.ordersRepository.save(x);
                    }

            for (Order x : orders)
                if (x.getShippingAddress() != null)
                    if (x.getShippingAddress().getId() == address.getId()) {
                        x.setShippingAddress(null);
                        RepositoriesAccess.ordersRepository.save(x);
                    }
            for (User x : users)
                if (x.getAddress() != null)
                    if (x.getAddress().getId() == address.getId()) {
                        x.setAddress(null);
                        RepositoriesAccess.usersRepository.save(x);
                    }

            RepositoriesAccess.addressRepository.delete(address);
        }
        Iterable<Address> allAddress = RepositoriesAccess.addressRepository.findAll();
        model.addAttribute("address", allAddress);

        return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/address/delete");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteFromInputText(@RequestParam("id") Long id, Model model) {
        Address address = RepositoriesAccess.addressRepository.findById(id);

        if (address != null) {
            Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();
            Iterable<User> users = RepositoriesAccess.usersRepository.findAll();

            for (Order x : orders)
                if (x.getBillingAddress() != null)
                    if (x.getBillingAddress().getId() == address.getId()) {
                        x.setBillingAddress(null);
                        RepositoriesAccess.ordersRepository.save(x);
                    }

            for (Order x : orders)
                if (x.getShippingAddress() != null)
                    if (x.getShippingAddress().getId() == address.getId()) {
                        x.setShippingAddress(null);
                        RepositoriesAccess.ordersRepository.save(x);
                    }

            for (User x : users)
                if (x.getAddress() != null)
                    if (x.getAddress().getId() == address.getId()) {
                        x.setAddress(null);
                        RepositoriesAccess.usersRepository.save(x);
                    }

            RepositoriesAccess.addressRepository.delete(address);
            model.addAttribute("msg", "Succes");
        }
        Iterable<Address> allAdress = RepositoriesAccess.addressRepository.findAll();
        model.addAttribute("address", allAdress);

        return "/administratorSite/addressManager/delete";
    }
}
