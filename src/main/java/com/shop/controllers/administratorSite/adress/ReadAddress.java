package com.shop.controllers.administratorSite.adress;

import com.shop.data.services.AddressService;
import com.shop.data.tables.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("administratorSite/address")
public class ReadAddress {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String readSite(Model model) {
        Iterable<Address> address = addressService.findAll();

        model.addAttribute("allAddress", address);
        return "administratorSite/addressManager/read";
    }

    @RequestMapping(value = "readOne", method = RequestMethod.GET)
    public String readOne(@RequestParam("id") String id, Model model) {
        Iterable<Address> allAddress = addressService.findAll();
        Address address = addressService.findOne(Long.parseLong(id));

        if (allAddress == null) {
            model.addAttribute("msg", "not found");
            model.addAttribute("allAddress", allAddress);
            return "administratorSite/addressManager/read";
        }
        model.addAttribute("address", address);
        return "administratorSite/addressManager/read";
    }
}
