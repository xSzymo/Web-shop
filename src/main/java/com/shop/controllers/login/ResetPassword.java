package com.shop.controllers.login;

import com.shop.data.services.UsersService;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ResetPassword {
    @Autowired
    private static UsersService usersService;

    @RequestMapping(value = "reset", method = RequestMethod.POST)
    public String resetPassword(@RequestParam(name = "password") String password, Model model,
                                HttpServletRequest request) {
        String eMail = (String) request.getSession().getAttribute("email");

        if ((boolean) request.getSession().getAttribute("authorize") == true) {
            User user = usersService.findByEmail(eMail);
            user.setPassword(password);
            usersService.save(user);

            model.addAttribute("msg", "Success");
            model.addAttribute("Success", "Success");
            request.getSession().removeAttribute("code");
            request.getSession().removeAttribute("authorize");
            request.getSession().removeAttribute("email");
        } else
            model.addAttribute("msg", "Something went wrong");
        return "loginAndRegistration/reset/resetPassword";
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public String codeSite(@RequestParam(name = "code") String code, HttpServletResponse response,
                           HttpServletRequest request, Model model) {

        boolean codeAccepted = false;

        if (request.getSession().getAttribute("code").equals(code)) {
            codeAccepted = true;
            request.getSession().setAttribute("authorize", true);
        } else {
            model.addAttribute("msg", "Woops, wrong code");
            request.getSession().setAttribute("authorize", false);
        }
        model.addAttribute("codeAccepted", codeAccepted);

        return "loginAndRegistration/reset/resetPassword";
    }

    public String findCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("code"))
                    return cookie.getValue();
        return "-1";
    }
}
