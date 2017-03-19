package com.shop.controllers.administratorSite.categories;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Pictures;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;
import com.shop.*;

@Controller
@RequestMapping("administratorSite/categories")
public class CategoriesCRUD {

	@RequestMapping
	public String start() {
		return "administratorSite/categoriesStartPage";
	}

	@RequestMapping("create")
	public String createSite() {
		return "administratorSite/categoriesManager/create";
	}

	@RequestMapping("/createCategory")
	public String create1(@RequestParam("name") String name,  Model model) {

		Categories categories = RepositoriesAccess.categoriesRepository.findByName(name);

		if (categories != null) 
			model.addAttribute("msgError", "Category already exist");

		Categories category = new Categories(name);
		 
		RepositoriesAccess.categoriesRepository.save(category);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/categoriesManager/create";
	}

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<com.shop.data.tables.Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("categories", categories);
		return "administratorSite/categoriesManager/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("name") String name, Model model) {
		 com.shop.data.tables.Categories category = RepositoriesAccess.categoriesRepository.findByName(name);
		 
		if (category == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/categoriesManager/read";
		}
		model.addAttribute("category", category);
		return "administratorSite/categoriesManager/read";
	}

	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		
		model.addAttribute("categories", categories);
		return "administratorSite/categoriesManager/update";
	}

	@RequestMapping("updateCategory/update")
	public String updateBook(@RequestParam("id") String id, @RequestParam("name") String name, Model model) {

		Categories category = RepositoriesAccess.categoriesRepository.findById(Long.parseLong(id));
		
		if (category == null) {
			model.addAttribute("msg", "not found category to update");
			return "administratorSite/categoriesManager/updateOneCategory";
		}

		category.setName(name);
		
		RepositoriesAccess.categoriesRepository.save(category);
		model.addAttribute("category", category);
		model.addAttribute("msg", "Success");
		return "administratorSite/categoriesManager/updateOneCategory";
	}

	@RequestMapping(value = "updateCategory/{bookId}")
	public String updateBook(@PathVariable Long bookId, Model model) {
		 Categories categoryFound = RepositoriesAccess.categoriesRepository.findById(bookId);

		if (categoryFound == null)
			model.addAttribute("msg", "not found");

		model.addAttribute("category", categoryFound);
		return "/administratorSite/categoriesManager/updateOneCategory";
	}

	
	@RequestMapping("delete")
	public String deleteSite(Model model) {
		 Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("categories", categories);
		return "administratorSite/categoriesManager/delete";
	}

	@RequestMapping(value = "deleteCategory/{categoryId}")
	public RedirectView deleteB(@PathVariable Long categoryId, Model model) {
		 Categories category = RepositoriesAccess.categoriesRepository.findById(categoryId);

		if (category == null)
			model.addAttribute("msg", "not found");// wont work for redirectView
		else {
			RepositoriesAccess.categoriesRepository.delete(category);
			model.addAttribute("msg", "Succes, back to delete more");// wont
																		// work
																		// for
																		// redirectView
		}
		 Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/categories/delete");
	}

	@RequestMapping(value = "deleteCategory")
	public String deleteB(@RequestParam("categoryName") String categoryName, Model model) {
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
