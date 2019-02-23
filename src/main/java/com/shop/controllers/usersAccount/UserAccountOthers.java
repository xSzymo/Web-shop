package com.shop.controllers.usersAccount;

import com.shop.controllers.login.Login;
import com.shop.data.services.UserRolesService;
import com.shop.data.services.UsersService;
import com.shop.data.tables.User;
import com.shop.data.tables.UserRole;
import com.shop.others.email.SendEmailDeleteAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

@Controller
@RequestMapping("/account")
public class UserAccountOthers {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserRolesService userRolesService;
    @Autowired
    private SendEmailDeleteAccount sendEmailDeleteAccount;
    @Autowired
    private Login login;

    @RequestMapping(value = "informations", method = RequestMethod.GET)
    public String userInformations(Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersService.findByLogin(user1.getLogin());
        Iterable<UserRole> found = userRolesService.findAll();

        UserRole ROLEPLAYING = null;

        for (UserRole x : found)
            for (Iterator<User> iterator = x.getUsers().iterator(); iterator.hasNext(); ) {
                User a = iterator.next();
                if (a.getId() == user.getId()) {
                    ROLEPLAYING = x;
                }
            }

        model.addAttribute("role", ROLEPLAYING);
        model.addAttribute("user", user);
        return "userAccount/options/informations";
    }

    @RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("password") String password, @RequestParam("password1") String password1,
                                Model model, HttpServletRequest request) {
        User user = usersService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (password.equals(password1))
            if (user.getPassword().equals(password))
                sendEmailDeleteAccount.sendCode(user, request);

        return "userAccount/options/deletAccount";
    }

    @RequestMapping(value = "deleteAccountCode/{code}", method = RequestMethod.GET)
    public String deleteAccountWithCode(@PathVariable String code, HttpServletRequest request, HttpServletResponse response, Model model) {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (request.getSession().getAttribute("userName").equals(login))
            if (request.getSession().getAttribute("deleteAccountCode").equals(code)) {
                User user = usersService.findByLogin(login);
                usersService.delete(user.getId());
            }
        return this.login.logout(request, response, model);
    }
}
