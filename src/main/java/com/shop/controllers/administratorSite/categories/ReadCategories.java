package com.shop.controllers.administratorSite.categories;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Categories;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/categories")
public class ReadCategories {

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("categories", categories);
		return "administratorSite/categoriesManager/read";
	}

	@RequestMapping(value = "read", method = RequestMethod.POST)
	public String readOne(@RequestParam("name") String name, Model model) {
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		Categories category = RepositoriesAccess.categoriesRepository.findByName(name);

		if (category == null) {
			model.addAttribute("msg", "not found");
			model.addAttribute("categories", categories);
			return "administratorSite/categoriesManager/read";
		}
		model.addAttribute("category", category);
		return "administratorSite/categoriesManager/read";
	}
}
