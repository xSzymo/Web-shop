package com.shop.controllers.administratorSite.books;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class ReadBooks {

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();
		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("books", books);
		model.addAttribute("categories", categories);
		return "administratorSite/booksCRUD/read";
	}

	@RequestMapping(value = "read", method = RequestMethod.POST)
	public String readOne(@RequestParam("name") String name, Model model) {
		Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();
		Book book = RepositoriesAccess.booksRepository.findByName(name);

		if (book == null) {
			Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
			model.addAttribute("msg", "not found");
			model.addAttribute("books", books);
			model.addAttribute("categories", categories);
			return "administratorSite/booksCRUD/read";
		}

		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
		for (Category x : categories)
			for (Book x1 : x.getBooks())
				if (x1.getId() == book.getId())
					model.addAttribute("category", x);

		model.addAttribute("book", book);
		return "administratorSite/booksCRUD/read";
	}
}
