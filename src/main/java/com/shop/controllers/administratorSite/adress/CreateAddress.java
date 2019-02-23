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
public class CreateAddress {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createSite() {
        return "administratorSite/addressManager/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
                         @RequestParam("city") String city, @RequestParam("country") String country, Model model) {
        Address address = new Address(street, postalCode, city, country);

        addressService.save(address);
        model.addAttribute("msgSuccess", "success");

        return "administratorSite/addressManager/create";
    }
}
