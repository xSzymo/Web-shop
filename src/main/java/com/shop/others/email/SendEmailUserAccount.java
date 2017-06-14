package com.shop.others.email;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SendEmailUserAccount {

    public static void sendEmailWithOrder(String text, String eMail, HttpServletRequest request) {
        try {
            Session session = EmailActions.authorizeWebShopEmail();

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(ApplicationProperties.SHOP_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(eMail, false));
            msg.setSubject("Shop order");
            msg.setText(text);
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (MessagingException e) {
            System.out.println("Error : " + e);
        }
    }

    public static boolean sendEmailWithNewPassswordOrEmail(String email, String newPassword, Model model, HttpServletRequest request) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());

        boolean success = false;
        try {
            Session session = EmailActions.authorizeWebShopEmail();

            if (newPassword != null && email.equals("")) {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(ApplicationProperties.SHOP_EMAIL));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.geteMail(), false));

                msg.setSubject("New password");
                msg.setText("New password : " + newPassword);
                msg.setSentDate(new Date());
                Transport.send(msg);

                user.setPassword(newPassword);
                RepositoriesAccess.usersRepository.save(user);
                success = true;
            } else {
                String code = Long.toHexString(Double.doubleToLongBits(Math.random()));
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(ApplicationProperties.SHOP_EMAIL));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.geteMail(), false));

                msg.setSubject("Change Email");
                msg.setText("Your code : " + code);
                msg.setSentDate(new Date());
                Transport.send(msg);

                request.getSession().setAttribute("code", code);
                success = true;
            }

        } catch (MessagingException e) {
            System.out.println("Error : " + e);
        }
        return success;
    }
}
