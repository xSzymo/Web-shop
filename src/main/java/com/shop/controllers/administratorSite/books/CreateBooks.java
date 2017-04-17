package com.shop.controllers.administratorSite.books;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Picture;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class CreateBooks {

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createSite(Model model) {
		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		return "administratorSite/booksCRUD/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestParam("name") String name, @RequestParam("author") String author,
			@RequestParam("description") String description, @RequestParam("langauge") String langauge,
			@RequestParam("price") String price, @RequestParam("pictureName") String pictureName, Model model,
			HttpServletRequest request) {

		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		String categoryName = null;
		boolean moreThanOne = false;

		for (Category x : categories) {
			if (request.getParameter(x.getName()) != null) {
				categoryName = x.getName();

				if (moreThanOne) {
					model.addAttribute("msgError", "Check only 1 category");
					return "administratorSite/booksCRUD/create";
				}
				moreThanOne = true;
			}
		}
		if (moreThanOne == false) {
			model.addAttribute("msgError", "You have to check 1 category");
			return "administratorSite/booksCRUD/create";
		}

		Category category = RepositoriesAccess.categoriesRepository.findByName(categoryName);
		Picture picture = RepositoriesAccess.picturesRepository.findByName(pictureName);

		if (picture == null) {
			model.addAttribute("msgError", "No category and picture(optional) found");
		}
		Book book = new Book(name, author, langauge, description, new BigDecimal(price));
		book.getPictures().add(picture);
		RepositoriesAccess.booksRepository.save(book);
		category.getBooks().add(book);
		RepositoriesAccess.categoriesRepository.save(category);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/booksCRUD/create";
	}
}
