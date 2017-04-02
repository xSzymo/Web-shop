package com.shop.controllers.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.controllers.shop.actions.OrderActions;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;
import com.shop.others.email.SendEmailUserAccount;


@Controller
@RequestMapping("shop")
public class AcceptOrder {

	@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("accept")
	public String acceptForUser(HttpServletRequest request, Model model,
			@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,

			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry,
			@RequestParam("couponCode") String couponCode, @RequestParam("email") String email,
			@RequestParam("payment") Object payment) {

		Users user = RepositoriesAccess.usersRepository
				.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

		String text = OrderActions.saveOrderAndReturnMessage(shippingAddressStreet, shippingAddressPostalCode, shippingAddressCity,
				shippingAddressCountry, billingAddressStreet, billingAddressPostalCode, billingAddressCity,
				billingAddressCountry, payment, couponCode, user.geteMail(), request);

		SendEmailUserAccount.sendEmailWithOrder(text, user.geteMail(), request);

		model.addAttribute("success", "success");
		return "shop/options/userOrder";
	}

	@RequestMapping("acceptAnonymous")
	public String acceptForAnonymous(HttpServletRequest request, Model model,
			@RequestParam("shippingAddressStreet") String shippingAddressStreet,
			@RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
			@RequestParam("shippingAddressCity") String shippingAddressCity,
			@RequestParam("shippingAddressCountry") String shippingAddressCountry,

			@RequestParam("billingAddressStreet") String billingAddressStreet,
			@RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
			@RequestParam("billingAddressCity") String billingAddressCity,
			@RequestParam("billingAddressCountry") String billingAddressCountry, @RequestParam("email") String email,
			@RequestParam(name = "couponCode", defaultValue = "") String couponCode,
			@RequestParam("payment") Object payment) {

		String text = OrderActions.saveOrderAndReturnMessage(shippingAddressStreet, shippingAddressPostalCode, shippingAddressCity,
				shippingAddressCountry, billingAddressStreet, billingAddressPostalCode, billingAddressCity,
				billingAddressCountry, payment, couponCode, email, request);

		SendEmailUserAccount.sendEmailWithOrder(text, email, request);

		model.addAttribute("success", "success");
		return "shop/options/userOrder";
	}
}
