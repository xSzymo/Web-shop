package com.shop.others.email;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;


public class SendEmailForgetPasswordLogin {
	@RequestMapping("sendCode")
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
			msg.setFrom(new InternetAddress("examplewebshop@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("szymeksss@wp.pl", false));
			//msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.geteMail(), false));
			msg.setSubject("Reset password");
			msg.setText("After 5 min code will be delete\n" + code);
			msg.setSentDate(new Date());
			Transport.send(msg);

		} catch (MessagingException e) {
			System.out.println("Error : " + e);
		}
		return "loginAndRegistration/reset/codePassword";
	}
	
	@RequestMapping("sendUsername")
	public String sendUsername(@RequestParam("email") String email, Model model, HttpServletResponse response,
			HttpServletRequest request) {

		if ((RepositoriesAccess.usersRepository.findByeMail(email) == null)) {
			model.addAttribute("msg", "Wrong e-mail");
			return "loginAndRegistration/reset/forgotUsername";
		}
		Users user = RepositoriesAccess.usersRepository.findByeMail(email);

		try {
			Session session = EmailActions.authorizeWebShopEmail();

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("examplewebshop@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("szymeksss@wp.pl", false));
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
