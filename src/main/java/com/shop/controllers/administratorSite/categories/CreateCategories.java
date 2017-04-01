package com.shop.controllers.administratorSite.categories;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Categories;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/categories")
public class CreateCategories {

	@RequestMapping("create")
	public String createSite() {
		return "administratorSite/categoriesManager/create";
	}

	@RequestMapping("/createCategory")
	public String create(@RequestParam("name") String name,  Model model) {

		Categories categories = RepositoriesAccess.categoriesRepository.findByName(name);

		if (categories != null) 
			model.addAttribute("msgError", "Category already exist");

		Categories category = new Categories(name);
		 
		RepositoriesAccess.categoriesRepository.save(category);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/categoriesManager/create";
	}
}
