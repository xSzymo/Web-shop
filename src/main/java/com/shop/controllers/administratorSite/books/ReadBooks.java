package com.shop.controllers.administratorSite.books;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class ReadBooks {

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("books", books);
		model.addAttribute("categories", categories);
		return "administratorSite/booksCRUD/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("name") String name, Model model) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		Books book = RepositoriesAccess.booksRepository.findByName(name);

		if (book == null) {
			Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
			model.addAttribute("msg", "not found");
			model.addAttribute("books", books);
			model.addAttribute("categories", categories);
			return "administratorSite/booksCRUD/read";
		}
		
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		for (Categories x : categories)
			for (Books x1 : x.getBooks())
				if (x1.getId() == book.getId()) 
					model.addAttribute("category", x);
				
		model.addAttribute("book", book);
		return "administratorSite/booksCRUD/read";
	}
}
