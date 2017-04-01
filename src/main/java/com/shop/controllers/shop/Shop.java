package com.shop.controllers.shop;


import java.util.HashSet;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("shop")
public class Shop {
	
	@RequestMapping
	public String start(Model model) {
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
