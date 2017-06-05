package com.shop.controllers.usersAccount;


import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import com.shop.others.email.SendEmailUserAccount;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/account")
public class ChangeUserData {

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("password") String password, @RequestParam("password1") String password1,
                                 Model model, HttpServletRequest request) {
        if (password.equals(password1))
            SendEmailUserAccount.sendEmailWithNewPassswordOrEmail("", password, model, request);

        return "userAccount/options/changePassword";
    }

    @RequestMapping(value = "changeAccEmail", method = RequestMethod.POST)
    public String changeEmail(@RequestParam("eMail") String eMail, Model model, HttpServletRequest request) {
        SendEmailUserAccount.sendEmailWithNewPassswordOrEmail(eMail, "", model, request);
        model.addAttribute("eMail", eMail);
        return "userAccount/options/changeEmailWithCode";
    }

    @RequestMapping(value = "changeEmailCode", method = RequestMethod.POST)
    public String accepNewEmail(@RequestParam("eMail") String eMail, @RequestParam("code") String code, Model model,
                                HttpServletRequest request) {
        if (request.getSession().getAttribute("code").equals(code)) {
            User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());
            user.seteMail(eMail);
            RepositoriesAccess.usersRepository.save(user);
            request.getSession().removeAttribute("code");
            model.addAttribute("msg", "success");
        } else {
            model.addAttribute("msg", "wrong code");
        }
        model.addAttribute("eMail", eMail);
        return "userAccount/userAccount";
    }
}
