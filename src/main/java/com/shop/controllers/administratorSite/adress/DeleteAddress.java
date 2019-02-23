package com.shop.controllers.administratorSite.adress;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.services.AddressService;
import com.shop.data.tables.Address;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteSite(Model model) {
        Iterable<Address> address = addressService.findAll();

        model.addAttribute("address", address);
        return "administratorSite/addressManager/delete";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public RedirectView deleteFromButton(@PathVariable Long id, Model model) {
        Address address = addressService.findOne(id);

        if (address != null)
            addressService.delete(address);

        Iterable<Address> allAddress = addressService.findAll();
        model.addAttribute("address", allAddress);

        return new RedirectView(ApplicationProperties.PROJECT_NAME + "administratorSite/address/delete");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteFromInputText(@RequestParam("id") Long id, Model model) {
        Address address = addressService.findOne(id);

        if (address != null) {
            addressService.delete(address);
            model.addAttribute("msg", "Succes");
        }
        Iterable<Address> allAdress = addressService.findAll();
        model.addAttribute("address", allAdress);

        return "/administratorSite/addressManager/delete";
    }
}
