package com.shop.controllers.administratorSite.users;

import com.shop.data.operations.UserDAO;
import com.shop.data.services.AddressService;
import com.shop.data.services.UserRolesService;
import com.shop.data.services.UsersService;
import com.shop.data.tables.Address;
import com.shop.data.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("administratorSite/users")
public class CreateUsers {
    @Autowired
    private UsersService usersService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRolesService userRolesService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createSite() {
        return "administratorSite/usersManager/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("login") String login, @RequestParam("password") String password,
                         @RequestParam("name") String name, @RequestParam("surname") String surname,
                         @RequestParam("date") String date, @RequestParam("eMail") String eMail,
                         @RequestParam("address") Long addressId, Model model, HttpServletRequest request) {
        User foundUser = usersService.findByLogin(login);

        Address address = null;
        if (addressId != null)
            address = addressService.findOne(addressId);

        String adminRole = request.getParameter("Admin");
        String userRole = request.getParameter("User");

        if (foundUser != null) {
            model.addAttribute("msgError", "That user already exist");
            return "administratorSite/usersManager/create";
        }
        if (login.equals("") || login.length() < 2) {
            model.addAttribute("msgError", "Wrong login");
            return "administratorSite/usersManager/create";
        }
        User user = new User(login, password, name, surname, eMail);
        if (date.equals(""))
            user.setAge(0);
        else
            user.setAge(UserDAO.convertDateIntoYears(date));

        if (address == null) {
            model.addAttribute("msgError", "No address found(optional)");
        } else
            user.setAddress(address);

//        if (request.getParameter("Admin") != null)
//            UserDAO.addUserWithRoles(adminRole, user);
//        else if (request.getParameter("User") != null)
//            UserDAO.addUserWithRoles(userRole, user);
//        else
        usersService.save(user);

        model.addAttribute("msgSuccess", "success");
        return "administratorSite/usersManager/create";
    }

    @RequestMapping(value = "/createAddress", method = RequestMethod.POST)
    public String createAddress(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
                                @RequestParam("city") String city, @RequestParam("country") String country, Model model) {
        Address address = new Address(street, postalCode, city, country);

        addressService.save(address);
        model.addAttribute("address", address);

        return "administratorSite/usersManager/create";
    }
}
