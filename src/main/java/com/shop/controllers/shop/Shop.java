package com.shop.controllers.shop;

import java.util.HashSet;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("shop")
public class Shop {
	@Autowired
	private RememberMeServices rememberMeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String start(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (request.getRequestedSessionId() != null)
			rememberMeService.autoLogin(request, response);

		if (!(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")))
			model.addAttribute("logged", true);
		else
			model.addAttribute("logged", false);

		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);
		return "shopStartPage";
	}

	@SuppressWarnings("unchecked")
	public static HashSet<Books> getBasket(HttpServletRequest request) {
		return (HashSet<Books>) request.getSession().getAttribute("basket");
	}

	@SuppressWarnings("unchecked")
	public static LinkedList<Books> getBasketWithAllBooks(HttpServletRequest request) {
		return (LinkedList<Books>) request.getSession().getAttribute("basketWithAllBooks");
	}
}
