package com.shop.controllers.administratorSite.users;

import com.shop.data.operations.UserDAO;
import com.shop.data.services.AddressService;
import com.shop.data.services.UserRolesService;
import com.shop.data.services.UsersService;
import com.shop.data.tables.Address;
import com.shop.data.tables.User;
import com.shop.data.tables.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("administratorSite/users")
public class UpdateUsers {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserRolesService userRolesService;
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model) {
        Iterable<User> users = usersService.findAll();
        Iterable<UserRole> roles = userRolesService.findAll();
        Iterable<Address> address = addressService.findAll();

        model.addAttribute("address", address);
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administratorSite/usersManager/update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable Long id, Model model, HttpServletRequest request) {
        User foundUser = usersService.findOne(id);

        if (foundUser == null)
            model.addAttribute("msg", "not found");

        model.addAttribute("user", foundUser);
        model.addAttribute("address", foundUser.getAddress());
        return "/administratorSite/usersManager/updateOneUser";
    }

    @RequestMapping(value = "update/update", method = RequestMethod.POST)
    public String updateBook(@RequestParam("id") String id, @RequestParam("login") String login,
                             @RequestParam("password") String password, @RequestParam("name") String name,
                             @RequestParam("surname") String surname, @RequestParam("date") String date,
                             @RequestParam("eMail") String eMail, @RequestParam(name = "addressId", required = false) Long addressId,
                             Model model, HttpServletRequest request) {
        Address address = null;

        if (addressId != null)
            address = addressService.findOne(addressId);
        User foundUser = usersService.findOne(Long.parseLong(id));

        if (foundUser == null) {
            model.addAttribute("book", foundUser);
            model.addAttribute("msg", "not found user to update");
            return "administratorSite/usersManager/updateOneBook";
        }
        if (address == null)
            model.addAttribute("msg", "not found address");
        else
            foundUser.setAddress(address);

        String adminRole = request.getParameter("Admin");
        String userRole = request.getParameter("User");

        foundUser.setName(name);
        foundUser.setLogin(login);
        foundUser.setPassword(password);
        foundUser.setName(name);
        foundUser.setSurname(surname);
        foundUser.seteMail(eMail);
        if (date.equals(""))
            foundUser.setAge(0);
        else
            foundUser.setAge(UserDAO.convertDateIntoYears(date));

//        if (request.getParameter("Admin") != null)
//            UserDAO.addUserWithRoles(adminRole, foundUser);
//        else if (request.getParameter("User") != null)
//            UserDAO.addUserWithRoles(userRole, foundUser);
//        else
        usersService.save(foundUser);
        model.addAttribute("msg", "success");
        model.addAttribute("user", foundUser);
        model.addAttribute("address", address);
        return "administratorSite/usersManager/updateOneUser";
    }

    @RequestMapping(value = "update/createAddress", method = RequestMethod.POST)
    public String createAddress(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
                                @RequestParam("city") String city, @RequestParam("country") String country,
                                @RequestParam("userId") Long userId, Model model) {
        User user = usersService.findOne(userId);
        Address address = new Address(street, postalCode, city, country);

        addressService.save(address);
        model.addAttribute("address", address);
        model.addAttribute("user", user);

        return "/administratorSite/usersManager/updateOneUser";
    }
}
