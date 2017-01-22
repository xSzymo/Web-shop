package com.shop.controllers;

import java.util.Properties;

import javax.mail.Session;

import java.util.Date;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Users;
import com.shop.others.RunAtStart;

@Controller
public class ResetPasswdActions {
	
	public static Users user;
	
	@RequestMapping("sendCode")
	public String sendCode(@RequestParam("login") String login, @RequestParam("email") String email, Model model,
			HttpServletResponse response, HttpServletRequest request) {

		if ((RunAtStart.usersRepository.findByeMail(email) == null)) {
			model.addAttribute("msg", "Wrong e-mail");
			return "loginAndRegistration/reset/forgotPassword";
		} else if (RunAtStart.usersRepository.findByLogin(login) == null) {
			model.addAttribute("msg", "Wrong login");
			return "loginAndRegistration/reset/forgotPassword";
		}
		Users user = RunAtStart.usersRepository.findByeMail(email);

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "examplewebshop@gmail.com";
		final String password = "ZAQ!2wsx";
		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			String code = Long.toHexString(Double.doubleToLongBits(Math.random()));
			Cookie cookie = new Cookie("code", code);
			// cookie.setPath("/WebShop");
			// cookie.setHttpOnly(true);
			cookie.setMaxAge(300);

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("examplewebshop@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.geteMail(), false));
			msg.setSubject("Reset password");
			msg.setText("After 5 min code will be delete\n" + code);
			msg.setSentDate(new Date());
			Transport.send(msg);
			
			response.addCookie(cookie);
			this.user = user;
		} catch (MessagingException e) {
			System.out.println("Error : " + e);
		}		

		System.out.println("USER");
		System.out.println(this.user.getLogin());
		System.out.println(this.user.getPassword());
		System.out.println(this.user.geteMail());

		return "loginAndRegistration/reset/codePassword";
	}

	@RequestMapping("sendUsername")
	public String sendUsername(@RequestParam("email") String email, Model model, HttpServletResponse response,
			HttpServletRequest request) {

		if ((RunAtStart.usersRepository.findByeMail(email) == null)) {
			model.addAttribute("msg", "Wrong e-mail");
			return "loginAndRegistration/reset/forgotUsername";
		}
		Users user = RunAtStart.usersRepository.findByeMail(email);

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "examplewebshop@gmail.com";
		final String password = "ZAQ!2wsx";
		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("examplewebshop@gmail.com"));
			 msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.geteMail(), false));
			msg.setSubject("Reset password");
			msg.setText("Login : " + user.getLogin());
			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (MessagingException e) {
			System.out.println("Error : " + e);
		}

		model.addAttribute("msg", "Success");

		return "loginAndRegistration/reset/forgotUsername";
	}

	@RequestMapping("reset")
	public String resetPassword1(@RequestParam(name = "password") String password, Model model) {

		this.user.setPassword(password);
		RunAtStart.usersRepository.save(user);
		model.addAttribute("msg", "Success");
		
		return "loginAndRegistration/reset/resetPassword";
	}

	@RequestMapping("resetPassword")
	public String resetPassword1(@RequestParam(name = "code") String code, HttpServletResponse response,
			HttpServletRequest request, Model model) {

		boolean codeAccepted = false;

		if (code.equals(findCookie("code", request))) {
			codeAccepted = true;
		} else
			model.addAttribute("msg", "Woops, wrong code");

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
