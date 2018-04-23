package com.shop.controllers.usersAccount;

import com.shop.data.services.AddressService;
import com.shop.data.services.UsersService;
import com.shop.data.tables.Address;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class UpdateUserData {
    @Autowired
    private UsersService usersService;
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateUserInformations(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                         @RequestParam("age") String age, @RequestParam("addressId") Long addressId,
                                         @RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
                                         @RequestParam("city") String city, @RequestParam("country") String country, Model model,
                                         HttpServletRequest request) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersService.findByLogin(user1.getLogin());
        Address address = null;

        if (addressId != null)
            address = addressService.findOne(addressId);
        if (address == null)
            address = new Address();

        address.setCity(city);
        address.setCountry(country);
        address.setPostalCode(postalCode);
        address.setStreet(street);
        user.setAddress(address);
        addressService.save(address);

        user.setName(name);
        user.setSurname(surname);
        user.setAge(Integer.parseInt(age));
        usersService.save(user);

        model.addAttribute("msg", "success");
        model.addAttribute("user", user);
        model.addAttribute("address", address);
        return "userAccount/options/changeData";
    }

    @RequestMapping(value = "createAddress", method = RequestMethod.POST)
    public String createAddress(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
                                @RequestParam("city") String city, @RequestParam("country") String country, Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersService.findByLogin(user1.getLogin());
        Address address = new Address(street, postalCode, city, country);

        addressService.save(address);
        model.addAttribute("address", address);
        model.addAttribute("user", user);

        return "userAccount/options/changeData";
    }
}
