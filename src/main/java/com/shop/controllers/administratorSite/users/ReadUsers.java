package com.shop.controllers.administratorSite.users;

import com.shop.data.services.AddressService;
import com.shop.data.services.UserRolesService;
import com.shop.data.services.UsersService;
import com.shop.data.tables.Address;
import com.shop.data.tables.User;
import com.shop.data.tables.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("administratorSite/users")
public class ReadUsers {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserRolesService userRolesService;
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String readSite(Model model, HttpServletRequest request) {
        Iterable<User> users = usersService.findAll();
        Iterable<UserRole> roles = userRolesService.findAll();
        Iterable<Address> address = addressService.findAll();

        model.addAttribute("address", address);
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "administratorSite/usersManager/read";
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public String readOne(@RequestParam("login") String login, Model model) {
        User users = usersService.findByLogin(login);
        Iterable<UserRole> roles = userRolesService.findAll();

        if (users == null) {
            model.addAttribute("msg", "not found");
            return "administratorSite/usersManager/read";
        }

        model.addAttribute("roles", roles);
        model.addAttribute("user", users);
        model.addAttribute("address", users.getAddress());
        return "administratorSite/usersManager/read";
    }
}
