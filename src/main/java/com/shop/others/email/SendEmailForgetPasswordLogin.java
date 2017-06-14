package com.shop.others.email;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class SendEmailForgetPasswordLogin {
    @RequestMapping(value = "sendCode", method = RequestMethod.POST)
    public String sendCode(@RequestParam("login") String login, @RequestParam("email") String email, Model model,
                           HttpServletResponse response, HttpServletRequest request) {

        if ((RepositoriesAccess.usersRepository.findByeMail(email) == null)) {
            model.addAttribute("msg", "Wrong e-mail");
            return "loginAndRegistration/reset/forgotPassword";
        } else if (RepositoriesAccess.usersRepository.findByLogin(login) == null) {
            model.addAttribute("msg", "Wrong login");
            return "loginAndRegistration/reset/forgotPassword";
        }

        try {
            Session session = EmailActions.authorizeWebShopEmail();

            String code = Long.toHexString(Double.doubleToLongBits(Math.random()));
            request.getSession().setAttribute("code", code);
            request.getSession().setAttribute("email", email);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(ApplicationProperties.SHOP_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject("Reset password");
            msg.setText("After 5 min code will be delete\n" + code);
            msg.setSentDate(new Date());
            Transport.send(msg);

        } catch (MessagingException e) {
            System.out.println("Error : " + e);
        }
        return "loginAndRegistration/reset/codePassword";
    }

    @RequestMapping(value = "sendUsername", method = RequestMethod.POST)
    public String sendUsername(@RequestParam("email") String email, Model model, HttpServletResponse response,
                               HttpServletRequest request) {

        if ((RepositoriesAccess.usersRepository.findByeMail(email) == null)) {
            model.addAttribute("msg", "Wrong e-mail");
            return "loginAndRegistration/reset/forgotUsername";
        }
        User user = RepositoriesAccess.usersRepository.findByeMail(email);

        try {
            Session session = EmailActions.authorizeWebShopEmail();

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(ApplicationProperties.SHOP_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject("Your login");
            msg.setText("Login : " + user.getLogin());
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (MessagingException e) {
            System.out.println("Error : " + e);
        }

        model.addAttribute("msg", "Success");

        return "loginAndRegistration/reset/forgotUsername";
    }


}
