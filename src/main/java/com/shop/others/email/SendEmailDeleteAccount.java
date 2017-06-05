package com.shop.others.email;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.User;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class SendEmailDeleteAccount {
    public static String sendCode(User user, HttpServletRequest request) {
        try {
            Session session = EmailActions.authorizeWebShopEmail();

            String code = Long.toHexString(Double.doubleToLongBits(Math.random()));
            request.getSession().setAttribute("deleteAccountCode", code);
            request.getSession().setAttribute("userName", user.getLogin());
            System.out.println(code);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(ApplicationConfig.SHOP_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.geteMail(), false));
            msg.setSubject("Delete account");
            msg.setText("Link : " + ApplicationConfig.URL + ApplicationConfig.PROJECT_NAME + "account/deleteAccountCode/" + code);
            msg.setSentDate(new Date());
            Transport.send(msg);

        } catch (MessagingException e) {
            System.out.println("Error : " + e);
        }
        return "loginAndRegistration/reset/codePassword";
    }
}
