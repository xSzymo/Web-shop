package com.shop.controllers.administratorSite.categories;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Category;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/categories")
public class CreateCategories {

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createSite() {
		return "administratorSite/categoriesManager/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestParam("name") String name, Model model) {

		Category categories = RepositoriesAccess.categoriesRepository.findByName(name);

		if (categories != null)
			model.addAttribute("msgError", "Category already exist");

		Category category = new Category(name);

		RepositoriesAccess.categoriesRepository.save(category);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/categoriesManager/create";
	}
}
