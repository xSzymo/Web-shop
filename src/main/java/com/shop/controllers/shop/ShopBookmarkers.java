package com.shop.controllers.shop;

import java.util.HashSet;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("shop")
public class ShopBookmarkers {

	@RequestMapping("{categoryName}")
	public String shopBookmarker(@PathVariable String categoryName, Model model) {
		Categories category = RepositoriesAccess.categoriesRepository.findByName(categoryName);
		model.addAttribute("books", category.getBooks());
		return "shop/categorySite";
	}

	@RequestMapping("categorySite/{id}")
	public RedirectView addItemToBasket(@PathVariable Long id, @RequestParam("number") String number,
			HttpServletRequest request, Model model) {
		Books book = RepositoriesAccess.booksRepository.findById(id);
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		Categories category = null;
		for (Categories x : categories) {
			for (Books x1 : x.getBooks()) {
				if (x1.getId() == x1.getId()) {
					category = x;
				}
			}
		}
		if (number == null || number.equals(""))
			return new RedirectView(ApplicationConfig.projectName + "shop/" + category.getName());

		LinkedList<Books> basketWithAllBooks = Shop.getBasketWithAllBooks(request);
		HashSet<Books> basket = Shop.getBasket(request);

		boolean is = false;
		for (Books x : basket)
			if (x.getId() == book.getId())
				is = true;

		if (is) {
			for (int i = 0; i < Long.parseLong(number); i++)
				basketWithAllBooks.add(book);
		} else {
			for (int i = 0; i < Long.parseLong(number); i++)
				basketWithAllBooks.add(book);
			basket.add(book);
		}
		request.getSession().setAttribute("basketWithAllBooks", basketWithAllBooks);
		request.getSession().setAttribute("basket", basket);

		return new RedirectView(ApplicationConfig.projectName + "shop/" + category.getName());
	}
}
