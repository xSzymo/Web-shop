package com.shop.controllers.administratorSite.adress;

import com.shop.data.services.AddressService;
import com.shop.data.tables.Address;
import com.shop.others.RepositoriesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("administratorSite/address")
public class UpdateAddress {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSite(Model model) {
        Iterable<Address> address = addressService.findAll();

        model.addAttribute("allAddress", address);
        return "administratorSite/addressManager/update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateOneSite(@PathVariable Long id, Model model) {
        Address address = addressService.findOne(id);

        if (address == null)
            model.addAttribute("msg", "not found");

        model.addAttribute("address", address);
        return "/administratorSite/addressManager/updateOneAddress";
    }

    @RequestMapping(value = "update/update", method = RequestMethod.POST)
    public String update(@RequestParam("city") String city, @RequestParam("country") String country,
                         @RequestParam("postalCode") String postalCode, @RequestParam("street") String street,
                         @RequestParam("id") String id, Model model) {

        Address address = addressService.findOne(Long.parseLong(id));

        if (address == null) {
            model.addAttribute("msg", "not found category to update");
            return "administratorSite/couponCodesManager/updateOneAddress";
        }

        address.setCity(city);
        address.setCountry(country);
        address.setPostalCode(postalCode);
        address.setStreet(street);

        addressService.save(address);
        model.addAttribute("address", address);
        model.addAttribute("msg", "Success");
        return "administratorSite/addressManager/updateOneAddress";
    }
}
