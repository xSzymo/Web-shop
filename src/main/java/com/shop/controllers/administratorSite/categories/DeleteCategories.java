package com.shop.controllers.administratorSite.categories;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Categories;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/categories")
public class DeleteCategories {
	
	@RequestMapping("delete")
	public String deleteSite(Model model) {
		 Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("categories", categories);
		return "administratorSite/categoriesManager/delete";
	}

	@RequestMapping(value = "deleteCategory/{categoryId}")
	public RedirectView deleteFromButton(@PathVariable Long categoryId, Model model, RedirectAttributes red) {
		 Categories category = RepositoriesAccess.categoriesRepository.findById(categoryId);

		if (category == null)
			red.addFlashAttribute("msg", "not found");
		else {
			RepositoriesAccess.categoriesRepository.delete(category);
			red.addFlashAttribute("msg", "Succes");
		}
		 Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/categories/delete");
	}

	@RequestMapping(value = "deleteCategory")
	public String deleteFromInputText(@RequestParam("categoryName") String categoryName, Model model) {
		 Categories categoryFound = RepositoriesAccess.categoriesRepository.findByName(categoryName);

		if (categoryFound == null)
			model.addAttribute("msg", "not found");
		else {
			RepositoriesAccess.categoriesRepository.delete(categoryFound);
			model.addAttribute("msg", "Succes");
		}

		 Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		 
		model.addAttribute("categories", categories);
		return "/administratorSite/categoriesManager/delete";
	}
}
