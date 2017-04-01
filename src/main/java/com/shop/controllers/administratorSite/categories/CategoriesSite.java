package com.shop.controllers.administratorSite.categories;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administratorSite/categories")
public class CategoriesSite {

	@RequestMapping
	public String start() {
		return "administratorSite/categoriesStartPage";
	}
}
