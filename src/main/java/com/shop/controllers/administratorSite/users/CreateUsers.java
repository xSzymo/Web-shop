package com.shop.controllers.administratorSite.users;

import com.shop.data.operations.UserDAO;
import com.shop.data.tables.Address;
import com.shop.data.tables.User;
import com.shop.data.tables.UserRole;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("administratorSite/users")
public class CreateUsers {

    @SuppressWarnings("deprecation")
    public static Date getDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        Date newDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return newDate;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createSite() {
        return "administratorSite/usersManager/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("login") String login, @RequestParam("password") String password,
                         @RequestParam("name") String name, @RequestParam("surname") String surname,
                         @RequestParam("date") String date, @RequestParam("eMail") String eMail,
                         @RequestParam("address") Long addressId, Model model, HttpServletRequest request) {
        User foundUser = RepositoriesAccess.usersRepository.findByLogin(login);

        Address address = null;
        if (addressId != null)
            address = RepositoriesAccess.addressRepository.findById(addressId);

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

        if (request.getParameter("Admin") != null)
            addUserWithRoles(adminRole, user);
        else if (request.getParameter("User") != null)
            addUserWithRoles(userRole, user);
        else
            RepositoriesAccess.usersRepository.save(user);

        model.addAttribute("msgSuccess", "success");
        return "administratorSite/usersManager/create";
    }

    @RequestMapping(value = "/createAddress", method = RequestMethod.POST)
    public String createAddress(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
                                @RequestParam("city") String city, @RequestParam("country") String country, Model model) {
        Address address = new Address(street, postalCode, city, country);

        RepositoriesAccess.addressRepository.save(address);
        model.addAttribute("address", address);

        return "administratorSite/usersManager/create";
    }

    public void addUserWithRoles(String role, User user) {
        Iterable<UserRole> users = RepositoriesAccess.userRolesRepository.findAll();
        for (UserRole x : users)
            if (x.getRole().equals(role)) {
                for (User x1 : x.getUsers()) {
                    if (x1.getId() == user.getId()) {
                        RepositoriesAccess.usersRepository.save(user);
                        return;
                    }
                }
                x.getUsers().add(user);
                RepositoriesAccess.userRolesRepository.save(x);
                return;
            }
    }
}
